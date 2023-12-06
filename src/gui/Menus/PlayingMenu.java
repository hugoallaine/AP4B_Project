package src.gui.Menus;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.gui.CardButton;
import src.gui.MKButton;
import src.gui.MKMenu;
import src.jeu.Game;

public final class PlayingMenu extends MKMenu {
    private final JPanel mainPanel;
    private final ArrayList<CardButton> cardButtons;
    private final JLabel nameLabel;
    private final MKButton nextPlayerButton;
    private final MKButton playCardButton;
    private final JPanel cardsPanel;
    private final MKButton actionButton;
    private final JLabel playerLevelLabel;

    public PlayingMenu() {
        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(Color.RED);
        this.mainPanel.setLayout(new GridBagLayout());

        this.nameLabel = new JLabel();
        this.nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.cardsPanel = new JPanel();
        GridLayout cardLayout = new GridLayout(1,0);
        cardLayout.setHgap(5);
        cardLayout.setVgap(5);
        this.cardsPanel.setLayout(cardLayout);
        this.cardsPanel.setBackground(new Color(0x00FFFFFF, true));
    
        JPanel actionButtonsPanel = new JPanel();
        actionButtonsPanel.setLayout(new GridLayout(1,0));
        actionButtonsPanel.setBackground(new Color(0x00FFFFFF, true));
        ((GridLayout)actionButtonsPanel.getLayout()).setHgap(5);

        this.nextPlayerButton = new MKButton("Next Player");

        this.playCardButton = new MKButton("Play card");

        this.actionButton = new MKButton("ActionButton");

        this.playerLevelLabel = new JLabel();
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0f;
        gbc.weighty = 1.0f;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        this.mainPanel.add(this.nameLabel, gbc);

        gbc.gridx = 1;
        this.mainPanel.add(this.playerLevelLabel);
        gbc.gridx = 0;

        gbc.gridy = 1;
        this.cardButtons = new ArrayList<>(Game.MAX_CARD_IN_HAND);
        this.mainPanel.add(this.cardsPanel, gbc);


        actionButtonsPanel.add(this.nextPlayerButton, gbc);
        actionButtonsPanel.add(this.playCardButton);
        actionButtonsPanel.add(this.actionButton);

        gbc.gridy++;

        this.mainPanel.add(actionButtonsPanel, gbc);
        this.hide();
    }

    public MKButton getActionButton() {
        return this.actionButton;
    }


    public MKButton getNextPlayerButton() {
        return this.nextPlayerButton;
    }

    public MKButton getPlayCardButton() {
        return this.playCardButton;
    }

    public JLabel getNameLabel() {
        return this.nameLabel;
    }

    public JLabel getPlayerLevelLabel() {
        return this.playerLevelLabel;
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    public void addCardButton(CardButton cb) {
        this.cardButtons.add(cb);
        this.cardsPanel.add(cb);
        this.cardsPanel.validate();
    } 

    public void clearCardButtons() {
        for(CardButton cb : this.cardButtons) {
            this.cardsPanel.remove(cb);
        }
        this.cardButtons.clear();
    }

    @Override
    public void hide() {
        this.mainPanel.setVisible(false);
        this.cardButtons.forEach(button -> button.setVisible(false));
    }

    @Override
    public void show() {
        this.mainPanel.setVisible(true);
        this.cardButtons.forEach(button -> button.setVisible(true));
    }
}