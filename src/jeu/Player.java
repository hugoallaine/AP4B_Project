package src.jeu;

import java.util.ArrayList;

import src.jeu.Cards.Card;
import src.jeu.Cards.Ethnicities;

public class Player {
    // enum Actions {
    //     DRAWING,
        
    // }
    private int level;
    private final String name;
    public String getName(){
        return this.name;
    }
    private final ArrayList<Card> hand;
    private final ArrayList<Card> stuff;
    private GameClasses gameClass;
    private Ethnicities ethnicity;
    private boolean hasDrawn;
    private int dodge = 4;

    public Player(String name){
        this.level = 1;
        this.name = name;
        this.hand = new ArrayList<>();
        this.stuff = new ArrayList<>();
        this.gameClass = null;
        this.ethnicity = null;
        this.hasDrawn = false;
    }

    public int getPower(){
        return this.level;
    }

    public int getDodge() {
        return this.dodge;
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
        StringBuilder output = new StringBuilder();
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

    public String getInfoString() {
        final String classString = this.gameClass == null ? "None" : this.gameClass.toString();
        final String ethnicityString = this.ethnicity == null ? "None" : this.ethnicity.toString();
        StringBuilder output = new StringBuilder();
        output.append("Level : ").append(level).append("\n")
              .append("Class : ").append(classString).append("\n")
              .append("Ethinicity : ").append(ethnicityString).append("\n")
              .append("Stuff : ").append(this.stuff).append("\n");
        return output.toString();
    }

    public void removeCardFromHand(Card card) {
        this.hand.remove(card);
    }
}
