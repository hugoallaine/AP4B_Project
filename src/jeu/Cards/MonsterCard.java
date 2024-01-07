package src.jeu.Cards;

import src.jeu.EffectsDefinitions;
import src.jeu.Player;

public final class MonsterCard extends EventCard {

    private final int strength;
    private final int treasure_amount;
    private final int xp;
    private int buffs;
    private int passiveID;

    public MonsterCard(String name, String desc , int strength, int treasure, int xp, int passiveID, int onDeathEffectID){
        super(name, desc + "(base strength :" + strength + ")", CardTargetMode.SELF);
        assert strength > 0 && treasure > 0 && xp > 0;
        this.strength = strength;
        this.treasure_amount = treasure;
        this.xp = xp;
        this.passiveID = passiveID;
        this.buffs = 0;
        this.effect = EffectsDefinitions.getEffectFunctionFromID(onDeathEffectID);
    }

    public int getStrength() {
        return this.strength + this.buffs;
    }

    public int getXP() {
        return this.xp;
    }

    public int getTreasureAmount() {
        return this.treasure_amount;
    }

    public void buff(int powerBuff) {
        this.buffs += powerBuff;
    }

    /**
     * On the monster card, this method represents the effect applied to the player fighting this monster if he loses
     */
    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }

    public int getPassive() {
        return this.passiveID;
    }
}
