package src.gui;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import src.jeu.Cards.Card;

public class CardButton extends MKButton{
    private final Card card;
    private static final int BORDER_WIDTH = 1;
    public CardButton(Card card) {
        super("");
        this.removeHighlight();
        this.card = card;
        this.update();
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
