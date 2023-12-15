package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public final class CurseCard extends EventCard {
    public CurseCard(String name, String description, CardTargetMode mode) {
        super(name, description, mode);
    }

    @Override
    public void applyEffect(ArrayList<Player> targets) {
        for(Player target : targets) {
            this.effectFunction.effect(target);
        }
    }

    @Override
    public void applyEffect(Player target) {
        this.effectFunction.effect(target);
    }

    @Override
    public void discard() {
        // TODO Auto-generated method stub
        
    }
}
