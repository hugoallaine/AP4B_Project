package src.gui.Menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import src.gui.GameWindow;
import src.gui.MKMenu;

public class EndMenu extends MKMenu {

    private static final int LAYOUT_GAP = 5;

    private final JPanel mainPanel;
    private final JTextField winnerText;
    private final JTextArea textArea;
    private final JPanel box;

    public EndMenu() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridBagLayout());
        this.mainPanel.setBackground(Color.BLACK);

        this.textArea = new JTextArea();
        this.textArea.setBackground(MainMenu.TRANSPARENT);
        this.textArea.setFont(GameWindow.DEFAULT_FONT);
        this.textArea.setForeground(Color.WHITE);
        this.textArea.setEditable(false);
        this.textArea.setFocusable(false);

        this.winnerText = new JTextField();
        this.winnerText.setFont(MKMenu.TITLE_FONT);
        this.winnerText.setForeground(Color.WHITE);
        this.winnerText.setBackground(MKMenu.BACKGROUND_COLOR);
        this.winnerText.setBorder(BorderFactory.createEmptyBorder());

        this.box = new JPanel();
        this.box.setBackground(MKMenu.BACKGROUND_COLOR);
        this.box.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        this.box.setPreferredSize(new Dimension(1200, 900));
        this.box.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(LAYOUT_GAP, LAYOUT_GAP, LAYOUT_GAP, LAYOUT_GAP);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.box.add(winnerText, gbc);
        this.box.add(textArea, gbc);
        this.mainPanel.add(box, gbc);
        this.hide();
    }

    public void setText(String winnerName, String endStats) {
        String[] lines = endStats.split("\n");
        this.winnerText.setText(winnerName + " won the game");
        this.textArea.setRows(lines.length - 1);
        this.textArea.setText(endStats);
    }

    @Override
    public void show() {
        this.mainPanel.setVisible(true);
        this.textArea.setVisible(true);
    }

    @Override
    public void hide() {
        this.mainPanel.setVisible(false);
        this.textArea.setVisible(false);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }
}
