package src.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import src.gui.Menus.MainMenu;

public class EndMenu extends MKMenu{
    private final JPanel mainPanel;
    private final JTextArea textArea;

    public EndMenu() {
        this.mainPanel = new JPanel();
        // this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.setBackground(Color.BLUE);

        this.textArea = new JTextArea();
        this.textArea.setBackground(MainMenu.TRANSPARENT);
        this.textArea.setFont(GameWindow.DEFAULT_FONT);
        this.textArea.setForeground(Color.WHITE);
        this.textArea.setEditable(false);
        this.textArea.setFocusable(false);
        this.mainPanel.add(textArea, BorderLayout.CENTER);

        this.hide();
    }

    public void setText(String text) {
        this.textArea.setText(text);
    }

    @Override
    public void show() {
        this.mainPanel.setVisible(true);
    }

    @Override
    public void hide() {
        this.mainPanel.setVisible(false);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }
}
