package src.jeu;

import java.util.ArrayList;

import src.jeu.Cards.Card;
import src.jeu.Cards.Ethnicities;

public class Player {
    private int level;
    private final String name;
    public String getName(){
        return this.name;
    }
    private final ArrayList<Card> hand;
    private final ArrayList<Card> stuff;
    private GameClasses gameClass;
    private Ethnicities ethnicity;

    public Player(String name){
        this.level = 1;
        this.name = name;
        this.hand = new ArrayList<>();
        this.stuff = new ArrayList<>();
        this.gameClass = null;
        this.ethnicity = null;
    }

    public int getPower(){
        return this.level;
    }
    public int getLevel(){
        return this.level;
    }
    public void levelUp(int i){
        this.level+=i;
    }

    public void addCard(Card card){
        this.hand.add(card);
    }

    public void setClass(GameClasses gameClass){
        this.gameClass = gameClass;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public ArrayList<Card> getStuff() {
        return this.stuff;
    }

    @Override
    public String toString(){
        StringBuffer output = new StringBuffer();
        output.append(name + "\n- Level : " + level + "\n");
        output.append("- Hand : ");
        for(Card card : hand){
            output.append(card + " | ");
        }
        output.append("\n- Stuff : ");
        for(Card card : stuff){
            output.append(card + " | ");
        }
        output.append("\n- Class : " + gameClass);
        output.append("\n- Ethnicity : " + ethnicity);
        return output.toString();
    }

    public void removeCardFromHand(Card card) {
        this.hand.remove(card);
    }
}
