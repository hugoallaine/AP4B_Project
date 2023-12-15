package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public final class StuffCard extends TreasureCard{
    StuffCard(String name, String desc, CardTargetMode targetMode){
        super(name, desc, targetMode);
    }

    @Override
    public final void applyEffect(ArrayList<Player> targets) {
        for(Player target : targets){
            this.effectFunction.effect(target);
        }
    }

    @Override
    public void applyEffect(Player target) {
        this.effectFunction.effect(target);
    }
    

    @Override
    public void discard() {
        
    }
}
