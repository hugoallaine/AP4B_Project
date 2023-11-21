package src.gui;

import src.jeu.Game;
import src.jeu.SamePlayerException;

public final class App extends GameWindow {
    private static final String APP_TITLE = "Munchkin UTBM";
    private static final int APP_WIDTH = 640;
    private static final int APP_HEIGHT = 480;

    private Game game;

    public App(){
        super(APP_TITLE, APP_WIDTH, APP_HEIGHT);
        game = new Game();
    }

    private void nameInputHandler(){
        String text = this.mainMenu.textField.getText();
        this.mainMenu.textField.setText("");
        try{
            game.addPlayer(text);
            this.mainMenu.textArea.setText("Current players :\n" + game.getPlayerString());
            if(this.game.getPlayerNum() >= 3){
                this.mainMenu.startGameButton.addActionListener(e -> this.startGame());
                this.mainMenu.startGameButton.MKShow();
            }
        }
        catch(SamePlayerException spex){
            this.announce("Cannot add player: " + text + "\nThis name is already in use!\n");
        }
        catch(Exception ex){
            this.mainMenu.addPlayerButton.removeActionListener(e -> nameInputHandler());
            this.mainMenu.addPlayerButton.MKHide();
            this.announce("Cannot add player: " + text + "\nThere already are 6 players!\n");
        }
    }

    public void launch(){
        this.mainMenu.textField.addActionListener(e -> nameInputHandler());
        this.mainMenu.addPlayerButton.addActionListener(e -> nameInputHandler());
    }

    public void startGame(){
        this.game.start();
        super.mainMenu.hide();
    }
}
