package src.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import src.jeu.Cards.Card;

/**
 * @brief Bouton représentant une carte dans l'interface graphique
 * @details Cette classe hérite de MKButton et permet de représenter une carte
 * dans l'interface graphique. Elle permet de mettre à jour le bouton en fonction
 * de la carte qu'il représente et de mettre en place un système de surbrillance
 * pour indiquer que le joueur peut jouer cette carte.
 * 
 * @param card La carte représentée par le bouton
 */
public class CardButton extends MKButton{
    private static final int BORDER_WIDTH      = 2;
    public static final Color MONSTER_COLOR    = new Color(200, 50, 20);
    public static final Color CURSE_COLOR      = new Color(150, 25, 75);
    public static final Color SINGLE_USE_COLOR = new Color(25, 125, 50);
    public static final Color STUFF_COLOR      = new Color(150, 125, 20);
    public static final Color LANGUAGE_COLOR   = new Color(100,50,0);
    public static final Color CLASS_COLOR      = new Color(50,50,200);
    
    private final Card card;

    /**
     * @brief Constructeur de la classe CardButton
     * @param card La carte représentée par le bouton
     */
    public CardButton(Card card) {
        super("");
        this.removeHighlight();
        this.card = card;
        this.update();
        super.setBackground(Color.GRAY);
        super.setFont(new Font(null, Font.PLAIN, 24));
        super.setToolTipText(this.card.getDescription());
    }

    /**
     * @brief Getter de la carte représentée par le bouton
     * @return La carte représentée par le bouton
     */
    public Card getCard(){
        return this.card;
    }

    /**
     * @brief Met à jour le bouton en fonction de la carte qu'il représente
     */
    public void update() {
        if(this.card != null) {
            this.MKShow();
            this.setText(this.card.getName());
        }
        if(this.card == null){
            super.MKHide();
        }
    }

    /**
     * @brief Met en place un système de surbrillance pour indiquer que le joueur peut jouer cette carte
     */
    public void highlight() {
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDER_WIDTH));
    }

    /**
     * @brief Supprime la surbrillance du bouton
     */
    public void removeHighlight() {
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
    }

    /**
     * @brief Supprime tous les listeners du bouton
     */
    public void clearListeners() {
        for(ActionListener l : this.getActionListeners()) {
            this.removeActionListener(l);
        }
    }

    /**
     * @brief Retourne une représentation textuelle du bouton
     * @return Une représentation textuelle du bouton
     */
    @Override
    public String toString() {
        return card.toString();
    }
}