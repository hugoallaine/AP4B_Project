package src.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import src.jeu.Cards.Card;

public class CardButton extends MKButton{
    private static final int BORDER_WIDTH = 2;
    public static final Color MONSTER_COLOR = new Color(200, 50, 20);
    public static final Color CURSE_COLOR = new Color(150, 25, 75);
    public static final Color SINGLE_USE_COLOR = new Color(25, 125, 50);
    public static final Color STUFF_COLOR = new Color(150, 125, 20);
    public static final Color ETHNICITY_COLOR = new Color(100,50,0);
    public static final Color CLASS_COLOR = new Color(50,50,200);
    
    private final Card card;

    public CardButton(Card card) {
        super("");
        this.removeHighlight();
        this.card = card;
        this.update();
        super.setBackground(Color.GRAY);
        super.setFont(new Font(null, Font.PLAIN, 24));
        super.setToolTipText(this.card.getDescription());
    }

    public Card getCard(){
        return this.card;
    }

    public void update() {
        if(this.card != null) {
            this.MKShow();
            this.setText(this.card.getName());
        }
        if(this.card == null){
            super.MKHide();
        }
    }

    public void highlight() {
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDER_WIDTH));
    }

    public void removeHighlight() {
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
    }

    public void clearListeners() {
        for(ActionListener l : this.getActionListeners()) {
            this.removeActionListener(l);
        }
    }

    @Override
    public String toString() {
        return card.toString();
    }
}
