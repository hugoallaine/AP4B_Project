package src.gui;

import src.jeu.Game;
import src.jeu.InvalidPlayerNameException;
import src.jeu.SamePlayerException;

/**
 * This class is the one tying the game class with the GUI
 */
public final class App extends GameWindow {
    private static final String APP_TITLE = "Munchkin UTBM";
    private static final int APP_WIDTH = 1600;
    private static final int APP_HEIGHT = 900;

    private Game game;

    public App(){
        super(APP_TITLE, APP_WIDTH, APP_HEIGHT);
        this.game = new Game();
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
        catch(Exception ex){
            super.mainMenu.addPlayerButton.removeActionListener(e -> nameInputHandler());
            super.mainMenu.addPlayerButton.setEnabled(false);
            super.announce("Cannot add player: " + text + "\nThere already are 6 players!");
        }
    }

    public void launch(){
        super.mainMenu.textField.addActionListener(e -> nameInputHandler());
        super.mainMenu.addPlayerButton.addActionListener(e -> nameInputHandler());
    }

    public void startGame(){
        this.game.start();
        super.mainMenu.hide();
    }
}
