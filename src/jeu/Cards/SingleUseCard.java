package src.jeu.Cards;

import src.jeu.EffectsDefinitions;
import src.jeu.Player;

public final class SingleUseCard extends TreasureCard {
    
    public SingleUseCard(String name, String desc, int prix, CardTargetMode targetMode, int EffectID){
        super(name, desc, targetMode);
        this.effect = EffectsDefinitions.getEffectFunctionFromID(EffectID);
    }

    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }

    public boolean canApplyEffect(Player target){
        return target.getLevel() != 9;
    }
}
