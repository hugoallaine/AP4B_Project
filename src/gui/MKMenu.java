package src.gui;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class MKMenu {
    public static final Color BACKGROUND_COLOR = new Color(0x202020);
    public static final Color TRANSPARENT = new Color(0x00FFFFFF, true);
    public abstract void hide();
    public abstract void show();
    public abstract JPanel getPanel();
}
