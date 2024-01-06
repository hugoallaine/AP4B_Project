package src.gui.Menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import src.gui.CardButton;
import src.gui.GameWindow;
import src.gui.MKButton;
import src.gui.MKMenu;
import src.jeu.Game;

public final class PlayingMenu extends MKMenu {
    private final JPanel mainPanel;
    private final ArrayList<CardButton> cardButtons;
    private final MKButton nextPlayerButton;
    private final MKButton playCardButton;
    private final JPanel cardsPanel;
    private final MKButton actionButton;
    private final JTextArea playerInfo;
    private final JLabel playerName;
    private static final int LAYOUT_GAP = 5;
    private static final Color PLAYING_MENU_BKG = new Color(0x50CC40);

    public PlayingMenu() {
        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(PLAYING_MENU_BKG);
        this.mainPanel.setLayout(new GridBagLayout());

        this.playerName = new JLabel();
        this.playerName.setHorizontalAlignment(SwingConstants.CENTER);
        this.playerName.setFont(new Font(null, Font.PLAIN, 36));

        this.playerInfo = new JTextArea();
        this.playerInfo.setBackground(MainMenu.TRANSPARENT);
        this.playerInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.playerInfo.setFont(GameWindow.DEFAULT_FONT);
        this.playerInfo.setFocusable(false);
        this.playerInfo.setEditable(false);
        this.playerInfo.setRows(4);

        this.cardsPanel = new JPanel();
        GridLayout cardLayout = new GridLayout(1,0);
        cardLayout.setHgap(LAYOUT_GAP);
        cardLayout.setVgap(LAYOUT_GAP);
        this.cardsPanel.setLayout(cardLayout);
        this.cardsPanel.setBackground(MKMenu.TRANSPARENT);
    
        JPanel actionButtonsPanel = new JPanel();
        actionButtonsPanel.setLayout(new GridLayout(1,0));
        actionButtonsPanel.setBackground(MKMenu.TRANSPARENT);
        ((GridLayout)actionButtonsPanel.getLayout()).setHgap(LAYOUT_GAP);

        this.nextPlayerButton = new MKButton("Next Player");

        this.playCardButton = new MKButton("Play card");

        this.actionButton = new MKButton("ActionButton");
        
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0f;
        gbc.weighty = 1.0f;
        gbc.gridx = 0;
        gbc.insets = new Insets(LAYOUT_GAP,LAYOUT_GAP,LAYOUT_GAP,LAYOUT_GAP);
        gbc.fill = GridBagConstraints.BOTH;
        this.mainPanel.add(this.playerName, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.mainPanel.add(this.playerInfo, gbc);
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 5.0f;
        gbc.weighty = 5.0f;
        this.cardButtons = new ArrayList<>(Game.MAX_CARD_IN_HAND);
        this.mainPanel.add(this.cardsPanel, gbc);


        gbc.weightx = 1.0f;
        gbc.weighty = 1.0f;
        actionButtonsPanel.add(this.nextPlayerButton, gbc);
        actionButtonsPanel.add(this.playCardButton);
        actionButtonsPanel.add(this.actionButton);

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
    
    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    public void setPlayerNameLabelText(String playerName) {
        this.playerName.setText(playerName);
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

    public void updatePlayerInfoDisplay(String playerInfoString) {
        this.playerInfo.setText(playerInfoString);
    }
}