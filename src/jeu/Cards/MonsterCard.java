package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public final class MonsterCard extends EventCard {

    private int strength;
    private int treasure_amount;
    private int xp;
    
    public MonsterCard(String name, String desc /*CardTargetMode targetMode*/, int strength, int treasure, int xp,int id_passif,int id_incident){
        super(name, desc, CardTargetMode.SELF);
        assert (strength > 0 && treasure > 0 && xp > 0);
        this.strength = strength;
        this.treasure_amount = treasure;
        this.xp = xp;
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

    @Override
    public void applyEffect(Player target) {
        this.effectFunction.effect(target);
    }

    @Override
    public void applyEffect(ArrayList<Player> targets) {
        for(final Player target : targets) {
            this.effectFunction.effect(target);
        }
    }

    @Override
    public void discard() {
        // TODO Auto-generated method stub
        
    }
}
