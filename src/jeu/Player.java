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
    public static final int MAX_LEVEL = 10;
    public static final int MIN_LEVEL = 1;

    private int level;
    private final String name;
    private final ArrayList<Card> hand;
    private final HashMap<EquipementSlot, StuffCard> stuff;
    private GameClasses gameClass;
    private Languages lang;
    private boolean hasDrawn;
    private int dodge = 4;
    private int buffs;

    
    public Player(String name) {
        this.level = 1;
        this.name = name;
        this.hand = new ArrayList<>();
        this.stuff = new HashMap<>();
        this.gameClass = null;
        this.lang = null;
        this.hasDrawn = false;
        this.buffs = 0;
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
        if(this.level > MAX_LEVEL) {
            this.level = MAX_LEVEL;
        }else if(this.level < MIN_LEVEL) {
            this.level = MIN_LEVEL;
        }
    }

    public void buff(int buff) {
        this.buffs += buff;
    }

    public void resetBuffs() {
        this.buffs = 0;
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

    public void removeRandomStuff() {
        if(this.stuff.isEmpty()) {
            return;
        }
        EquipementSlot rand;
        do{
            rand = EquipementSlot.getRandom();
        }while(this.stuff.containsKey(rand));
        System.out.println(this.stuff.remove(rand));
    }

    public int getStuffPower(){
        final AtomicInteger power = new AtomicInteger(0);
        stuff.forEach((k,card) -> {
            power.getAndAdd(card.getBonus());
        });
        return power.get();
    }

    public String getInfoString() {
        final String classString = this.gameClass == null ? "Aucune" : this.gameClass.toString();
        final String langString = this.lang == null ? "Aucune" : this.lang.toString();
        StringBuilder output = new StringBuilder();
        output.append("Niveau : ").append(level).append(" ".repeat(5)).append("Puissance : ").append(getPower()).append("\n")
              .append("Spécialisation : ").append(classString).append("\n")
              .append("Langue : ").append(langString).append("\n");
        output.append("Équipement : ");
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
