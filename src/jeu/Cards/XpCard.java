package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public final class XpCard extends TreasureCard {
    private int xp;
    public int getXP() {
        return this.xp;
    }

    public XpCard(String name, String desc, int xp, CardTargetMode targetMode){
        super(name, desc, targetMode);
        this.xp=xp;
        this.setEffectFunction((Player target) -> target.levelUp(this.xp));
    }

    @Override
    public void applyEffect(ArrayList<Player> targets) {
        for(Player target : targets) {
            if(this.canApplyEffect(target)) {
                this.effectFunction.effect(target);
            }
        }
    }

    public boolean canApplyEffect(Player target){
        return target.getLevel() != 9;
    }

    @Override
    public void discard() {
        // TODO Auto-generated method stub
        
    }
    
}
