package src.gui.Menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import src.gui.MKButton;
import src.gui.MKMenu;

public final class MainMenu extends MKMenu{
    public final JLabel mainLabel;
    public final JTextField textField;
    public final JTextArea textArea;
    public final JPanel mainPanel;
    public final MKButton addPlayerButton;
    public final MKButton startGameButton;
    private static final Color BACKGROUND_COLOR = new Color(0x202020);

    public MainMenu(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        // mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setPreferredSize(new Dimension(900, 600));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        mainLabel = new JLabel("Enter your name");
        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabel.setFont(new Font(null, Font.BOLD, 36));

        textField = new JTextField();
        textField.setToolTipText(" Name input ");
        textField.setFont(new Font("Liberation Mono", Font.PLAIN, 24));
        textField.setColumns(20);
        textField.setPreferredSize(new Dimension(this.mainPanel.getWidth(),50));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setMargin(new Insets(10, 10, 10, 10));
        // textArea.setPreferredSize(new Dimension(400, 200));

        addPlayerButton = new MKButton("Add player");
        startGameButton = new MKButton("Start game");
        startGameButton.setEnabled(false);

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
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy++;
        gbc.gridwidth = 2;
        mainPanel.add(textArea, gbc);
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        mainPanel.add(startGameButton, gbc);
        gbc.gridx++;
        mainPanel.add(addPlayerButton, gbc);
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
