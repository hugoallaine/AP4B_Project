package src.jeu;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Player {
    private int level;
    private final String name;
    public String getName(){
        return this.name;
    }
    private final ArrayList<Card> hand;
    private final ArrayList<Card> stuff;
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
    public int getlevel(){
        return level;
    }
    public void level_up(int i){
        this.level+=i;
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
