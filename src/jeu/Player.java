package src.jeu;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Player {
    private int level;
    private String name;
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
        String output = name + "\n\tLevel : " + level + "\n";
        output += "\tHand : ";
        for(Card card : hand){
            output += card + " | ";
        }
        output += "\n\tStuff : ";
        for(Card card : stuff){
            output += card + " | ";
        }
        output += "\n\tClass : " + gameClass;
        output += "\n\tEthnicity : " + ethnicity;
        return output;
    }
}
