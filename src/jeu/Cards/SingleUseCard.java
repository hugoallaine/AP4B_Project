package src.jeu.Cards;

import src.jeu.EffectsDefinitions;
import src.jeu.Player;

public final class SingleUseCard extends TreasureCard {
    int buff;
    
    public SingleUseCard(String name, String desc, int prix, int buff, CardTargetMode targetMode, int EffectID){
        super(name, desc +" (buffs the target by :"+buff+")", targetMode);
        this.buff = buff;
        this.effect = EffectsDefinitions.getEffectFunctionFromID(EffectID);
    }

    public int getBuff() {
        return this.buff;
    }

    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }

    public boolean canApplyEffect(Player target){
        return target.getLevel() != 9;
    }
}
