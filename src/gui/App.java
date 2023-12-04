package src.gui;

import java.util.ArrayList;

import src.jeu.Game;
import src.jeu.Cards.Card;
import src.jeu.Exceptions.InvalidPlayerNameException;
import src.jeu.Exceptions.SamePlayerException;
import src.jeu.Exceptions.TooManyCardsInHandException;
import src.jeu.Exceptions.TooManyPlayersException;

/**
 * This class is the one tying the game class with the GUI
 */
public final class App extends GameWindow {
    private static final String APP_TITLE = "Munchkin UTBM";
    private static final int APP_WIDTH = 1600;
    private static final int APP_HEIGHT = 900;
    private CardButton selectedCardButton;

    private Game game;

    public App(){
        super(APP_TITLE, APP_WIDTH, APP_HEIGHT);
        this.game = new Game();
        selectedCardButton = null;
    }

    private void nameInputHandler(){
        String text = super.mainMenu.textField.getText();
        super.mainMenu.textField.setText("");
        try{
            this.game.addPlayer(text);
            super.mainMenu.textArea.setText("Current players :\n" + game.getPlayerString());
            if(this.game.getPlayerNum() == 3){
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
            super.announce("Cannot add player: " + text + "\nThere already are 6 players!");
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
    }

    private void updateDisplay() {
        super.playingMenu.getNameLabel().setText(this.game.getCurrentPlayer().getName()+"'s turn");
        super.playingMenu.clearCardButtons();
        // faire en sorte qu'il puisse y avoir plus de 5 cartes sur l'écran pour quand le joueur pioche
        ArrayList<Card> currentPlayerHand = this.game.getCurrentPlayer().getHand();
        for(Card c : currentPlayerHand) {
            CardButton cardButton = new CardButton(c);
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
        }catch(TooManyCardsInHandException e){
            super.announce("You have to give up cards to continue");
        }
    }

    private void drawFromEventStack() {
        this.game.drawFromEventStack();
        this.update();
    }

    private void drawFromTreasureStack() {
        this.game.drawFromTreasureStack();
        this.update();
    }

    private void playSelectedCard(){
        if(this.selectedCardButton == null){
            super.announce("Cannot play a card because none are selected!");
            return;
        }
        this.selectedCardButton.getCard().applyEffect(this.game.getCurrentPlayer());
        this.game.getCurrentPlayer().removeCardFromHand(this.selectedCardButton.getCard());
        this.unselectCardButton(selectedCardButton);
        this.update();
    }

    private void selectCardButton(CardButton cb) {
        if(this.selectedCardButton != null){
            this.unselectCardButton(this.selectedCardButton);
        }
        cb.highlight();
        this.selectedCardButton = cb;
        this.selectedCardButton.clearListeners();
        this.selectedCardButton.addActionListener(e -> this.unselectCardButton(cb));
        super.repaint();
    }

    private void unselectCardButton(CardButton cb) {
        if(cb == null) {
            System.err.println("[ERROR] Ayayayo");
        }
        cb.removeHighlight();
        this.selectedCardButton = null;
        cb.clearListeners();
        cb.addActionListener(e -> this.selectCardButton(cb));
        super.repaint();
    }
}
