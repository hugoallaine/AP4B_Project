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

/**
 * @brief Classe du menu de jeu
 * @details Affiche le menu de jeu
 * 
 * @param mainPanel JPanel du menu
 * @param cardButtons ArrayList de CardButton pour afficher les cartes
 * @param nextPlayerButton MKButton pour passer au joueur suivant
 * @param playCardButton MKButton pour jouer une carte
 * @param cardsPanel JPanel pour afficher les cartes
 * @param actionButton MKButton pour effectuer une action
 * @param playerInfo JTextArea pour afficher les informations du joueur
 * @param playerName JLabel pour afficher le nom du joueur
 */
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

    /**
     * @brief Constructeur du menu de jeu
     * @details Initialise les composants du menu
     */
    public PlayingMenu() {
        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(MKMenu.BACKGROUND_COLOR);
        this.mainPanel.setLayout(new GridBagLayout());

        this.playerName = new JLabel();
        this.playerName.setHorizontalAlignment(SwingConstants.CENTER);
        this.playerName.setFont(new Font(null, Font.PLAIN, 36));
        this.playerName.setForeground(Color.WHITE);

        this.playerInfo = new JTextArea();
        this.playerInfo.setBackground(MKMenu.BACKGROUND_COLOR);
        this.playerInfo.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.playerInfo.setFont(GameWindow.DEFAULT_FONT);
        this.playerInfo.setEditable(false);
        this.playerInfo.setRows(4);
        this.playerInfo.setForeground(Color.WHITE);

        this.cardsPanel = new JPanel();
        GridLayout cardLayout = new GridLayout(1,0);
        cardLayout.setHgap(LAYOUT_GAP);
        cardLayout.setVgap(LAYOUT_GAP);
        this.cardsPanel.setLayout(cardLayout);
        this.cardsPanel.setBackground(MKMenu.BACKGROUND_COLOR);
    
        JPanel actionButtonsPanel = new JPanel();
        actionButtonsPanel.setLayout(new GridLayout(1,0));
        actionButtonsPanel.setBackground(MKMenu.BACKGROUND_COLOR);
        ((GridLayout)actionButtonsPanel.getLayout()).setHgap(LAYOUT_GAP);

        this.nextPlayerButton = new MKButton("Joueur suivant");

        this.playCardButton = new MKButton("Jouer la carte");

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

    /**
     * @brief Getter du bouton d'action
     * @return le bouton d'action
     */
    public MKButton getActionButton() {
        return this.actionButton;
    }

    /**
     * @brief Getter du bouton de joueur suivant
     * @return le bouton de joueur suivant
     */
    public MKButton getNextPlayerButton() {
        return this.nextPlayerButton;
    }

    /**
     * @brief Getter du bouton de jouer une carte
     * @return le bouton de jouer une carte
     */
    public MKButton getPlayCardButton() {
        return this.playCardButton;
    }
    
    /**
     * @brief Getter du JPanel du menu
     * @return JPanel du menu
     */
    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    /**
     * @brief Setter du nom du joueur
     * @param playerName Nom du joueur
     */
    public void setPlayerNameLabelText(String playerName) {
        this.playerName.setText(playerName);
    }

    /**
     * @brief Ajoute un bouton de carte
     * @param cb Bouton de carte à ajouter
     */
    public void addCardButton(CardButton cb) {
        this.cardButtons.add(cb);
        this.cardsPanel.add(cb);
        this.cardsPanel.validate();
    } 

    /**
     * @brief Supprime tous les boutons de carte
     */
    public void clearCardButtons() {
        for(CardButton cb : this.cardButtons) {
            this.cardsPanel.remove(cb);
        }
        this.cardButtons.clear();
    }

    /**
     * @brief Cache le menu
     */
    @Override
    public void hide() {
        this.mainPanel.setVisible(false);
        this.cardButtons.forEach(button -> button.setVisible(false));
        this.nextPlayerButton.MKHide();
        this.playCardButton.MKHide();
        this.cardsPanel.setVisible(false);
        this.actionButton.setVisible(false);
        this.playerInfo.setVisible(false);
        this.playerName.setVisible(false);
    }

    /**
     * @brief Affiche le menu
     */
    @Override
    public void show() {
        this.mainPanel.setVisible(true);
        this.cardButtons.forEach(button -> button.setVisible(true));
        this.nextPlayerButton.MKShow();
        this.playCardButton.MKShow();
        this.cardsPanel.setVisible(true);
        this.actionButton.setVisible(true);
        this.playerInfo.setVisible(true);
        this.playerName.setVisible(true);
    }

    /**
     * @brief Met à jour les informations du joueur
     * @param playerInfoString Informations du joueur
     */
    public void updatePlayerInfoDisplay(String playerInfoString) {
        this.playerInfo.setText(playerInfoString);
    }
}