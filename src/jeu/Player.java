package src.jeu;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Player {
    private int level;
    private final String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> stuff;
    private String gameClass, ethnicity;

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

    @Override
    public String toString(){
        String output = name + "\n- Level : " + level + "\n";
        output += "- Hand : ";
        for(Card card : hand){
            output += card + " | ";
        }
        output += "\n- Stuff : ";
        for(Card card : stuff){
            output += card + " | ";
        }
        output += "\n- Class : " + gameClass;
        output += "\n- Ethnicity : " + ethnicity;
        return output;
    }
}
