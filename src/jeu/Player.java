package src.jeu;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Player {
    private int level;
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> stuff;

    Player(String name){
        level = 1;
        this.name = name;
        hand = new ArrayList<>();
        stuff = new ArrayList<>();
    }

    public int power(){
        return level;
    }

    @Override
    public String toString(){
        String output = new String();
        output += name + "Level : " + level + "\n";
        output += "Hand : ";
        for(Card card : hand){
            output += card + " | ";
        }
        output += "\nStuff : ";
        for(Card card : stuff){
            output += card + " | ";
        }
        return output;
    }
}
