package src.jeu.Cards;

import src.jeu.Player;

public final class XpCard extends TreasureCard {
    private int xp;
    public int getXP() {
        return this.xp;
    }

    public XpCard(String name, String desc, int xp){
        super(name, desc);
        this.xp=xp;
        this.setEffectFunction((Player target) -> target.level_up(this.xp));
    }

    @Override
    public void applyEffect(Player target){
        this.effectFunction.effect(target);
    }

    public boolean verification(Player user,Player target){
        return target.getlevel() == 9;
    }

    @Override
    public void discard() {
        // TODO Auto-generated method stub
        
    }
    
}
