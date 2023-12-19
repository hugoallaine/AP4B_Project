package src.gui;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import src.jeu.Combat;
import src.jeu.Game;
import src.jeu.Player;
import src.jeu.Cards.Card;
import src.jeu.Cards.CardTargetMode;
import src.jeu.Cards.CurseCard;
import src.jeu.Cards.EventCard;
import src.jeu.Cards.MonsterCard;
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

    private void nextTurn() {
        try{
            this.game.nextTurn();
            this.playingMenu.clearCardButtons();
            this.update();
        }catch(TooManyCardsInHandException ex) {
            super.announce("You have to give up cards to continue");
        }catch(PlayerMustDrawException ex) {
            super.announce("You have to draw !");
        }
    }

    private void drawFromEventStack() {
        if(this.game.getCurrentPlayer().getHasDrawn()) {
            super.announce("You have already draw this turn");
        }
        try{
            final EventCard cardDrawn = this.game.drawFromEventStack();
            if(cardDrawn instanceof CurseCard) {
                this.game.applyCurseEffect((CurseCard) cardDrawn);
            }
            else if(cardDrawn instanceof MonsterCard) {
                //TODO: demander aux joueurs s'ils veulent jouer des cartes sur le monstre
                final Combat c = this.game.startCombat(this.game.getCurrentPlayer(), (MonsterCard) cardDrawn, new ArrayList<>());
                final boolean playerWon = c.fight();
                if(!playerWon) {
                    super.announce("You lost the fight!");
                }
            }
            else {
                this.game.getCurrentPlayer().addCard(cardDrawn);
            }
            this.updateDisplay();

        }catch(NoSuchElementException ex) {
            super.announce("Cannot draw from the event stack");
        }
    }

    private void drawFromTreasureStack() {
        try{
            this.game.getCurrentPlayer().addCard(this.game.drawFromTreasureStack());
            this.updateDisplay();
        }catch(NoSuchElementException ex) {
            super.announce("Cannot draw from the treasure card stack");
        }
    }

    private void playSelectedCard(){
        if(this.selectedCardButton == null) {
            super.announce("Cannot play a card because none are selected!");
            return;
        }
        final Card selectedCard = this.selectedCardButton.getCard();
        switch(selectedCard.getTargetMode()) {
        case SELF:
            selectedCard.applyEffect(this.game.getCurrentPlayer());
            break;
        case OTHER_PLAYER:
            selectedCard.applyEffect(this.askForTarget());
            break;
        case MONSTER:
            //TODO Creer un combat
            System.err.println("[ERROR] Unimplemented");
            break;
        case EVERYONE:
            // A card shouldn't be able to target everyone unless it's a curse card
            assert selectedCard instanceof CurseCard;
            final CurseCard selectedCurseCard = ((CurseCard)selectedCard);
            selectedCurseCard.applyEffect(this.game.getPlayers());
            break;
        }
        this.game.getCurrentPlayer().removeCardFromHand(selectedCard);
        this.game.discard(selectedCard);
        this.unselectCardButton(selectedCardButton);
        this.update();
    }

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

    private void unselectCardButton(final CardButton cb) {
        cb.removeHighlight();
        this.selectedCardButton = null;
        cb.clearListeners();
        cb.addActionListener(e -> this.selectCardButton(cb));
        super.repaint();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    // Faire en sorte que le joueur puisse annuler l'action
    private Player askForTarget() {
        final String[] playerNames =  this.playersToStringArray();
        int playerAnswer;
        do{
            playerAnswer = JOptionPane.showOptionDialog(null, "Choose a target", "Choose a target", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, playerNames, playerNames[0]);
        }while(playerAnswer == JOptionPane.CLOSED_OPTION);
        return this.game.getPlayers().get(playerAnswer);
    }

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

    private void updateActionButton(String buttonText, ActionListener l) {
        for(ActionListener al : super.playingMenu.getActionButton().getActionListeners()) {
            super.playingMenu.getActionButton().removeActionListener(al);
        }
        super.playingMenu.getActionButton().addActionListener(l);
        super.playingMenu.getActionButton().setText(buttonText);
    }
}
