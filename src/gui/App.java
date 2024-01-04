package src.gui;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import src.jeu.Combat;
import src.jeu.Game;
import src.jeu.Player;
import src.jeu.Cards.Card;
import src.jeu.Cards.CurseCard;
import src.jeu.Cards.EventCard;
import src.jeu.Cards.MonsterCard;
import src.jeu.Cards.SingleUseCard;
import src.jeu.Exceptions.InvalidPlayerNameException;
import src.jeu.Exceptions.PlayerMustDrawException;
import src.jeu.Exceptions.SamePlayerException;
import src.jeu.Exceptions.TooManyCardsInHandException;
import src.jeu.Exceptions.TooManyPlayersException;

public final class App extends GameWindow {
    private static final String APP_TITLE = "Munchkin UTBM";
    private static final int APP_WIDTH    = 1600;
    private static final int APP_HEIGHT   = 900;
    
    private final Game game;
    private CardButton selectedCardButton;

    public App(){
        super(APP_TITLE, APP_WIDTH, APP_HEIGHT);
        this.game = new Game();
        this.selectedCardButton = null;
    }

    private void nameInputHandler(){
        final String text = super.mainMenu.textField.getText();
        super.mainMenu.textField.setText("");
        try{
            this.game.addPlayer(text);
            super.mainMenu.textArea.setText("Current players :\n" + game.getPlayerString());
            if(this.game.getPlayerNum() == Game.MIN_PLAYER_NUM){
                super.mainMenu.startGameButton.addActionListener(e -> this.startGame());
                super.mainMenu.startGameButton.setEnabled(true);
            }
        }
        catch(InvalidPlayerNameException invalidNameEx){
            super.announce("The name you entered is invalid!");
        }
        catch(SamePlayerException spex){
            super.announce("Cannot add player: " + text + "\nThis name is already in use!");
        }
        catch(TooManyPlayersException ex){
            super.mainMenu.addPlayerButton.removeActionListener(e -> this.nameInputHandler());
            super.mainMenu.addPlayerButton.setEnabled(false);
            super.announce("Cannot add player: " + text + "\nThere already is 6 players!");
        }
    }

    public void launch(){
        super.mainMenu.textField.addActionListener(e -> nameInputHandler());
        super.mainMenu.addPlayerButton.addActionListener(e -> nameInputHandler());
    }

    public void startGame(){
        super.mainMenu.hide();
        this.game.start();
        super.playingMenu.show();
        this.update();
        super.playingMenu.getNextPlayerButton().addActionListener(e -> this.nextTurn());
        super.playingMenu.getPlayCardButton().addActionListener(e -> this.playSelectedCard());
        this.updateActionButton("Draw from event stack", (e -> this.drawFromEventStack()));
    }

    private void updateDisplay() {
        super.playingMenu.setPlayerNameLabelText(this.game.getCurrentPlayer().getName() + "'s turn");
        super.playingMenu.clearCardButtons();
        super.playingMenu.updatePlayerInfoDisplay(this.game.getCurrentPlayer().getInfoString());
        final ArrayList<Card> currentPlayerHand = this.game.getCurrentPlayer().getHand();
        for(final Card c : currentPlayerHand) {
            final CardButton cardButton = new CardButton(c);
            cardButton.addActionListener(l -> {
                this.selectCardButton(cardButton);
            });
            super.playingMenu.addCardButton(cardButton);
        }
        super.repaint();
    }

    private void update() {
        this.updateDisplay();
    }

    /**
     * Checks if the player can end their turn and if so, refreshes the interface and changes the current player
     */
    private void nextTurn() {
        Player player;
        if((player = this.game.isGameFinsihed()) != null) {
            super.announce("The game is finished, " + player.getName() + " won");
            return;
        }
        try {
            this.game.nextTurn();
            this.playingMenu.clearCardButtons();
            this.update();
        } catch(TooManyCardsInHandException ex) {
            super.announce("You have to give up cards to continue");
        } catch(PlayerMustDrawException ex) {
            super.announce("You have to draw !");
        }
    }

    /**
     * Pops the card at the top of the event cards stack and the following happens:
     * - if it is a curse: it is played immediatly
     * - if it is a monster: a fight between the monster and the current player starts
     * - otherwise, the card is put the current player's hand
     */
    private void drawFromEventStack() {
        if(this.game.getCurrentPlayer().getHasDrawn()) {
            super.announce("You have already drawn a card this turn");
            return;
        }
        try{
            final EventCard cardDrawn = this.game.drawFromEventStack();
            super.announce("You drew " + cardDrawn.getName());
            if(cardDrawn instanceof CurseCard) {
                this.game.applyCurseEffect((CurseCard) cardDrawn);
            }
            else if(cardDrawn instanceof MonsterCard) {
                MonsterCard monster = ((MonsterCard) cardDrawn);
                final int fightAnswer = JOptionPane.showConfirmDialog(null, "Do you want to fight" + monster.getName() + "?\nLevel : " + monster.getStrength());
                if(fightAnswer == JOptionPane.YES_OPTION) {
                    this.fightCombat((MonsterCard) cardDrawn);
                }
                else {
                    int diceResult = this.game.rollDice();
                    if(this.game.getCurrentPlayer().getDodge() < diceResult) {
                        super.announce("You died !");
                        return;
                    }
                }
                super.announce("You ran away !");
                return;
            }
            else {
                this.game.getCurrentPlayer().addCard(cardDrawn);
            }
            this.updateDisplay();

        }catch(NoSuchElementException ex) {
            super.announce("Cannot draw from the event stack");
        }
    }

    /**
     * Asks all the players which card they wish to play in the current fight
     * TODO: pour le moment on peut juste prendre une carte par joueur et on ne peut pas cibler le monstre ou le joueur (peut etre faire en sorte que on peut juste cibler le monstre)
     * @return
     */
    private ArrayList<Card> askForEffectCards() {
        final ArrayList<Card> result = new ArrayList<>();
        for(final Player p : this.game.getPlayers()) {

            // Selects only cards which are able to affect the monster
            final ArrayList<SingleUseCard> validCards = new ArrayList<>();
            for(final Card card : p.getHand()) {
                if(card instanceof SingleUseCard) {
                    validCards.add((SingleUseCard) card);
                }
            }

            if(validCards.size() == 0) {
                break;
            }

            final int answer = JOptionPane.showOptionDialog(null, p.getName() + " Choose a card to affect the current fight", "Choose a card", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, validCards.toArray(), validCards.get(0));
            if(answer > 0) {
                result.add(p.getHand().remove(answer));
            }
            System.out.println(result);
        }
        return result;
    }

    // private void drawFromTreasureStack() {
    //     try{
    //         this.game.getCurrentPlayer().addCard(this.game.drawFromTreasureStack());
    //         this.updateDisplay();
    //     }catch(NoSuchElementException ex) {
    //         super.announce("Cannot draw from the treasure card stack");
    //     }
    // }

    /**
     * Plays the card that the player selected via the card button
     */
    private void playSelectedCard(){
        if(this.selectedCardButton == null) {
            super.announce("Cannot play a card because none are selected!");
            return;
        }
        final Card selectedCard = this.selectedCardButton.getCard();
        this.game.getCurrentPlayer().removeCardFromHand(selectedCard);
        this.game.discard(selectedCard, this.game.getCurrentPlayer());
        this.unselectCardButton(selectedCardButton);
        this.update();

        if(selectedCard instanceof MonsterCard) {
            this.fightCombat((MonsterCard)selectedCard);
            return;
        }

        switch(selectedCard.getTargetMode()) {
        case SELF:
            selectedCard.applyEffect(this.game.getCurrentPlayer());
            break;
        case OTHER_PLAYER:
            final Player target = this.askForTarget();
            if(target != null) {
                selectedCard.applyEffect(target);
            }
            break;
        case MONSTER:
            System.err.println("[ERROR] Unimplemented");
            break;
        case EVERYONE:
            // A card shouldn't be able to target everyone unless it's a curse card
            assert selectedCard instanceof CurseCard;
            final CurseCard selectedCurseCard = ((CurseCard)selectedCard);
            selectedCurseCard.applyEffect(this.game.getPlayers());
            break;
        }
        this.update();
    }

    /**
     * Selects a card button by outlining it in a white outline
     * @param cb
     */
    private void selectCardButton(final CardButton cb) {
        if(this.selectedCardButton != null) {
            this.unselectCardButton(this.selectedCardButton);
        }
        cb.highlight();
        this.selectedCardButton = cb;
        this.selectedCardButton.clearListeners();
        this.selectedCardButton.addActionListener(e -> this.unselectCardButton(cb));
        super.repaint();
    }

    /**
     * Removes the white outline of the selected card button
     * @param cb
     */
    private void unselectCardButton(final CardButton cb) {
        if(cb == null) {
            System.err.println("[ERROR] Should not be possible");
        }
        cb.removeHighlight();
        this.selectedCardButton = null;
        cb.clearListeners();
        cb.addActionListener(e -> this.selectCardButton(cb));
        super.repaint();
    }

    //TODO Faire en sorte que le joueur puisse annuler l'action
    /**
     * When the player plays a card that targets some other player,
     * this method is called and creates a popup window asking which player the current player wants to target
     * @return the player chosen to be targeted
     */
    private Player askForTarget() {
        final String[] playerNames =  this.playersToStringArray();
        int playerAnswer = JOptionPane.showOptionDialog(null, "Choose a target", "Choose a target", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, playerNames, playerNames[0]);
        try {
            return this.game.getPlayers().get(playerAnswer);
        }catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Get the players' names in a string array suitable to be displayed in a multiple choice option dialog
     * @return
     */
    private String[] playersToStringArray() {
        final String[] players = new String[this.game.getPlayers().size()];
        int i = 0;
        for(final Player p : this.game.getPlayers()) {
            String pName = p.getName();
            if(p.equals(this.game.getCurrentPlayer())){
                pName += " (you)";
            }
            players[i] = pName;
            i++;
        }
        return players;
    }

    private void fightCombat(MonsterCard monster) {
        final ArrayList<Card> effectCards = this.askForEffectCards();
        final Combat c = this.game.startCombat(this.game.getCurrentPlayer(), (MonsterCard) monster, effectCards);
        
        final boolean playerWon = c.fight();
        if(!playerWon) {
            super.announce("You lost the fight!");
            return;
        }
        super.announce("You won, yay!");
        return;
        
    }

    /**
     * Changes the text and the action listener of the {@code actionButton} in the {@code PlayingMenu} class
     * Also removes every listeners present on the button
     * 
     * @param buttonText the text you want to diplay
     * @param l the new action listener for the button
     */
    private void updateActionButton(String buttonText, ActionListener l) {
        final MKButton actionButton = super.playingMenu.getActionButton();
        for(final ActionListener al : actionButton.getActionListeners()) {
            actionButton.removeActionListener(al);
        }
        actionButton.addActionListener(l);
        actionButton.setText(buttonText);
    }
}
