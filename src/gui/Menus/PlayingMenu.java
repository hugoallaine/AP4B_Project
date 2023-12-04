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
    protected final JPanel mainPanel;
    protected final ArrayList<CardButton> cardButtons;
    protected final JLabel nameLabel;
    protected final MKButton nextPlayerButton;
    protected final MKButton playCardButton;
    protected final JPanel cardsPanel;

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

        this.nextPlayerButton = new MKButton("Next Player");

        this.playCardButton = new MKButton("Play card");
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0f;
        gbc.weighty = 1.0f;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.RELATIVE;

        this.mainPanel.add(this.nameLabel, gbc);

        gbc.gridy = 1;
        this.cardButtons = new ArrayList<>(Game.MAX_CARD_IN_HAND);
        this.mainPanel.add(this.cardsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        this.mainPanel.add(this.nextPlayerButton, gbc);

        gbc.gridx = 1;
        this.mainPanel.add(this.playCardButton, gbc);
        this.hide();
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

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    public void addCardButton(CardButton cb) {
        this.cardButtons.add(cb);
        this.cardsPanel.add(cb);
    } 

    public void clearCardButtons() {
        for(CardButton cb : this.cardButtons) {
            this.cardsPanel.remove(cb);
        }
        this.cardButtons.clear();
    }

    @Override
    public void hide() {
        mainPanel.setVisible(false);
        cardButtons.forEach(button -> button.setVisible(false));
    }

    @Override
    public void show() {
        mainPanel.setVisible(true);
        cardButtons.forEach(button -> button.setVisible(true));
    }
}