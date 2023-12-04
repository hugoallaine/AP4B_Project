package src.jeu.Cards;

import src.jeu.Player;

public final class StuffCard extends TreasureCard{
    StuffCard(String name, String desc){
        super(name, desc);
    }

    @Override
    public final void applyEffect(Player target) {
        this.effectFunction.effect(target);
    }
    

    @Override
    public void discard() {
        
    }
}
