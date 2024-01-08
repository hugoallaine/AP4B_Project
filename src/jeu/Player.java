package src.jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import src.jeu.Cards.Card;
import src.jeu.Cards.EquipementSlot;
import src.jeu.Cards.GameClasses;
import src.jeu.Cards.Languages;
import src.jeu.Cards.StuffCard;

/**
 * @brief Classe d'un joueur
 * @details Un joueur possède un nom, un niveau, une main, un équipement, une classe et une ethnie
 * @param level Niveau du joueur
 * @param name Nom du joueur
 * @param hand Main du joueur
 * @param stuff Equipement du joueur
 * @param gameClass Classe du joueur
 * @param lang Ethnie du joueur
 * @param hasDrawn Indique si le joueur a déjà pioché une carte
 * @param dodge Esquive du joueur
 * @param buffs Buffs du joueur
 */
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
    
    /**
     * @brief Constructeur de la classe Player
     * @param name Nom du joueur
     */
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
    
    /**
     * @brief Getter du nom du joueur
     * @return Nom du joueur
     */
    public String getName() {
        return this.name;
    }

    /**
     * @brief Getter de la langue du joueur
     * @return Langue du joueur
     */
    public Languages getLanguage() {
        return this.lang;
    }
    
    /**
     * @brief Getter de la puissance du joueur
     * @return Puissance du joueur
     */
    public int getPower() {
        return this.level+this.getStuffPower()+this.buffs;
    }

    /**
     * @brief Getter de l'esquive du joueur
     * @return Esquive du joueur
     */
    public int getDodge() {
        return this.dodge;
    }

    /**
     * @brief Getter du niveau du joueur
     * @return Niveau du joueur
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * @brief Ajoute i niveau au joueur
     * @param i Nombre de niveau à ajouter
     */
    public void levelUp(int i) {
        this.level += i;
        if(this.level > MAX_LEVEL) {
            this.level = MAX_LEVEL;
        }else if(this.level < MIN_LEVEL) {
            this.level = MIN_LEVEL;
        }
    }

    /**
     * @brief Ajoute de la puissance bonus au joueur
     * @param buff buff à ajouter
     */
    public void buff(int buff) {
        this.buffs += buff;
    }

    /**
     * @brief Réinitialise les buffs du joueur à 0
     */
    public void resetBuffs() {
        this.buffs = 0;
    }

    /**
     * @brief Ajoute une carte à la main du joueur
     * @param card Carte à ajouter
     */
    public void addCard(Card card){
        this.hand.add(card);
    }

    /**
     * @brief Setter de la classe du joueur
     * @param gameClass Classe du joueur
     */
    public void setClass(GameClasses gameClass){
        this.gameClass = gameClass;
    }

    /**
     * @brief Setter de la langue du joueur
     * @param ethnie Langue du joueur
     */
    public void setLanguage(Languages ethnie){
        this.lang = ethnie;
    }

    /**
     * @brief Setter de l'état "a déjà pioché" du joueur
     * @param state Etat "a déjà pioché" du joueur
     */
    public void setHasDrawn(boolean state) {
        this.hasDrawn = state;
    }

    /**
     * @brief Getter de l'état "a déjà pioché" du joueur
     * @return Etat "a déjà pioché" du joueur
     */
    public boolean getHasDrawn() {
        return this.hasDrawn;
    }

    /**
     * @brief Getter de la main du joueur
     * @return Main du joueur
     */
    public ArrayList<Card> getHand() {
        return this.hand;
    }
    
    /**
     * @brief Ajoute un équipement au joueur
     * @details Si le joueur possède déjà un équipement dans le slot de l'équipement à ajouter, l'équipement est remplacé
     * @param card Equipement à ajouter
     */
    public void addStuff(StuffCard card){
        this.stuff.put(card.getEquipementSlot(), card);
    }

    /**
     * @brief Retire un équipement du joueur
     * @param card Equipement à retirer
     */
    public void removeStuff(StuffCard card){
        this.stuff.remove(card.getEquipementSlot());
    }

    /**
     * @brief Getter de l'équipement du joueur
     * @return Equipement du joueur
     */
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
    }

    /**
     * @brief Getter de la puissance de l'équipement du joueur
     * @return Puissance de l'équipement du joueur
     */
    public int getStuffPower(){
        final AtomicInteger power = new AtomicInteger(0);
        stuff.forEach((k,card) -> {
            power.getAndAdd(card.getBonus());
        });
        return power.get();
    }

    /**
     * @brief Crée une chaine de caractère contenant les informations du joueur
     * @return Chaine de caractère contenant les informations du joueur
     */
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
        if(this.stuff.isEmpty()) {
            output.append("rien :(");
        }
        return output.toString();
    }

    /**
     * @brief Retire une carte de la main du joueur
     * @param card Carte à retirer
     */
    public void removeCardFromHand(Card card) {
        this.hand.remove(card);
    }
}