package src.gui.Menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import src.gui.MKMenu;

public class PlayerSelectMenu extends MKMenu{
    protected final JPanel mainPanel;

    public PlayerSelectMenu() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridLayout(2, 0));
        this.mainPanel.setBackground(Color.WHITE);
        this.mainPanel.setPreferredSize(new Dimension(500,500));


        this.hide();
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

    @Override
    public void show() {
        this.mainPanel.setVisible(true);
    }

    @Override
    public void hide() {
        this.mainPanel.setVisible(false);
    }
}
