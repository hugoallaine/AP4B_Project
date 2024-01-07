package src.jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import src.jeu.Cards.Card;
import src.jeu.Cards.EquipementSlot;
import src.jeu.Cards.GameClasses;
import src.jeu.Cards.Languages;
import src.jeu.Cards.StuffCard;

public class Player {
    private int level;
    private final String name;

    private final ArrayList<Card> hand;
    private final HashMap<EquipementSlot, StuffCard> stuff;
    private GameClasses gameClass;
    private Languages lang;
    private boolean hasDrawn;
    private int dodge = 4;
    
    
    
    public Player(String name) {
        this.level = 1;
        this.name = name;
        this.hand = new ArrayList<>();
        this.stuff = new HashMap<>();
        this.gameClass = null;
        this.lang = null;
        this.hasDrawn = false;
    }
    
    public String getName() {
        return this.name;
    }

    public Languages getLanguage() {
        return this.lang;
    }
    
    public int getPower() {
        return this.level+this.getStuffPower();
    }

    public int getDodge() {
        return this.dodge;
    }

    public int getLevel() {
        return this.level;
    }

    public void levelUp(int i) {
        this.level += i;
    }

    public void addCard(Card card){
        this.hand.add(card);
    }

    public void setClass(GameClasses gameClass){
        this.gameClass = gameClass;
    }

    public void setLanguage(Languages ethnie){
        this.lang = ethnie;
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
        this.stuff.put(card.getEquipementSlot(), card);
    }

    public void removeStuff(StuffCard card){
        this.stuff.remove(card.getEquipementSlot());
    }

    public HashMap<EquipementSlot, StuffCard> getStuff() {
        return this.stuff;
    }

    public int getStuffPower(){
        final AtomicInteger power = new AtomicInteger(0);
        stuff.forEach((k,card) -> {
            power.getAndAdd(card.getBonus());
        });
        return power.get();
    }

    public String getInfoString() {
        final String classString = this.gameClass == null ? "None" : this.gameClass.toString();
        final String langString = this.lang == null ? "None" : this.lang.toString();
        StringBuilder output = new StringBuilder();
        output.append("Level : ").append(level).append("\n")
              .append("Class : ").append(classString).append("\n")
              .append("Language : ").append(langString).append("\n");
        output.append("Stuff : ");
        AtomicInteger size = new AtomicInteger(this.stuff.size());
        this.stuff.forEach((k,card) -> {
            output.append(card).append("(+").append(card.getBonus()).append(")");
            if(size.decrementAndGet() > 0) {
                output.append(" | ");
            }
        });
        return output.toString();
    }

    public void removeCardFromHand(Card card) {
        this.hand.remove(card);
    }
    
}
