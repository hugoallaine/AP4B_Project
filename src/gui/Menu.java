package src.gui;

import javax.swing.JPanel;

public abstract class Menu {
    public static final int MAIN_MENU = 0;
    public static final int PLAY_MENU = 1;
    public abstract void hide();
    public abstract void show();
    public abstract JPanel getPanel();
}
