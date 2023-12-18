package src.jeu;

import java.util.ArrayList;

import src.jeu.Cards.Card;
import src.jeu.Cards.Ethnicities;
import src.jeu.Cards.StuffCard;

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
    private final ArrayList<StuffCard> stuff;
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
        return this.level+this.getpowerstuff();
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

    public void setHasDrawn(boolean state) {
        this.hasDrawn = state;
    }

    public boolean getHasDrawn() {
        return this.hasDrawn;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }
    public void addStuff(StuffCard card){
        this.stuff.add(card);
    }

    public ArrayList<StuffCard> getStuff() {
        return this.stuff;
    }
    public int getpowerstuff(){
        int powerstuff=0;
        for(StuffCard card : stuff){

                powerstuff+=card.getBonus();
        }
        return powerstuff;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append(name + "\n- Level : " + level + "\n");
        output.append("- Hand : ");
        for(Card card : hand){
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
              .append("Ethinicity : ").append(ethnicityString).append("\n");
        output.append("Stuff : ");
        for(int i = 0; i < this.stuff.size() - 1; i++){
            output.append(this.stuff.get(i) + " | ");
        }
        if(this.stuff.size()>0){
            output.append(this.stuff.get(this.stuff.size()-1)).append("\n");
        }
        return output.toString();
    }

    public void removeCardFromHand(Card card) {
        this.hand.remove(card);
    }
}
