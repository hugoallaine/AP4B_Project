package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public final class MonsterCard extends EventCard {

    private int strength;
    private int treasure_amount;
    private int xp;
    private int id_passif;
    private int id_incident;

    public MonsterCard(String name, String desc , int strength, int treasure, int xp,int id_passif,int id_incident){
        super(name, desc, CardTargetMode.SELF);
        assert (strength > 0 && treasure > 0 && xp > 0);
        this.strength = strength;
        this.treasure_amount = treasure;
        this.xp = xp;
        this.id_passif = id_passif;
        this.id_incident = id_incident;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getXP() {
        return this.xp;
    }

    public int getTreasureAmount() {
        return this.treasure_amount;
    }

    public void buff(int powerBuff) {
        this.strength += powerBuff;
    }

    /**
     * On the monster card, this method represents the effect applied to the player fighting this monster if he loses
     */
    @Override
    public void applyEffect(Player target) {
//        this.effectFunction.effect(target);
        if(this.id_passif == 0){
            target.levelUp(-this.xp);
        }

    }
}
