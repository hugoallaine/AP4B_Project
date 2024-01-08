package src.gui.Menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import src.gui.GameWindow;
import src.gui.MKButton;
import src.gui.MKMenu;

public final class MainMenu extends MKMenu {
    public final JLabel mainLabel;
    public final JTextField textField;
    public final JTextArea textArea;
    public final JPanel mainPanel;
    public final MKButton addPlayerButton;
    public final MKButton startGameButton;
    

    public MainMenu(){
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridBagLayout());
        this.mainPanel.setBackground(MKMenu.BACKGROUND_COLOR);
        // mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.mainPanel.setPreferredSize(new Dimension(900, 600));
        this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        this.mainLabel = new JLabel("Entrez votre nom");
        this.mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.mainLabel.setFont(MKMenu.TITLE_FONT);
        this.mainLabel.setForeground(Color.WHITE);

        this.textField = new JTextField();
        this.textField.setFont(GameWindow.DEFAULT_FONT);
        this.textField.setColumns(20);
        this.textField.setPreferredSize(new Dimension(this.mainPanel.getWidth(),50));
        this.textField.setForeground(Color.WHITE);
        this.textField.setBackground(Color.GRAY);
        this.textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.textArea.setFocusable(false);
        this.textArea.setMargin(new Insets(10, 10, 10, 10));
        this.textArea.setFont(GameWindow.DEFAULT_FONT);
        this.textArea.setBackground(Color.GRAY);
        this.textArea.setForeground(Color.WHITE);
        this.textArea.setRows(3);
        // textArea.setPreferredSize(new Dimension(400, 200));

        this.addPlayerButton = new MKButton("Ajouter le joueur");
        this.startGameButton = new MKButton("Commencer la partie");
        this.startGameButton.setEnabled(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0f;
        gbc.weighty = 1.0f;
        gbc.gridx = 1;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(mainLabel,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy++;
        gbc.insets = new Insets(5, 40, 5, 40);
        mainPanel.add(textField, gbc);
        gbc.gridy++;
        mainPanel.add(textArea, gbc);
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridy++;
        mainPanel.add(startGameButton, gbc);
        gbc.gridx++;
        mainPanel.add(addPlayerButton, gbc);
    }

    public void updateTextArea(String text) {
        final int lines = text.split("\n").length;
        this.textArea.setRows(lines);
        this.textArea.setText(text);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    @Override
    public void hide() {
        this.addPlayerButton.MKHide();
        this.mainLabel.setVisible(false);
        this.mainPanel.setVisible(false);
        this.startGameButton.MKHide();
        this.textArea.setVisible(false);
        this.textField.setVisible(false);
    }

    @Override
    public void show() {
        this.addPlayerButton.MKShow();
        this.mainLabel.setVisible(true);
        this.mainPanel.setVisible(true);
        this.startGameButton.MKShow();
        this.textArea.setVisible(true);
        this.textField.setVisible(true);
    }
}
