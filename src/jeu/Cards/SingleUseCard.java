package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public final class SingleUseCard extends TreasureCard {
    public SingleUseCard(String name, String desc, int xp,int prix, CardTargetMode targetMode){
        super(name, desc, targetMode);
    }

    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }

    public boolean canApplyEffect(Player target){
        return target.getLevel() != 9;
    }
}
