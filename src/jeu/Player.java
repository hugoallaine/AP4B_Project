package src.jeu;

import java.util.ArrayList;

import org.w3c.dom.events.EventTarget;

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

    Player(String name){
        level = 1;
        this.name = name;
        hand = new ArrayList<>();
        stuff = new ArrayList<>();
        gameClass = null;
        ethnicity = null;
    }

    public int power(){
        return level;
    }
    public int getlevel(){
        return level;
    }
    public void level_up(int i){
        this.level+=i;
    }

    public void addCard(Card card){
        this.hand.add(card);
    }

    public void setClass(GameClasses gameClass){
        this.gameClass = gameClass;
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
}
