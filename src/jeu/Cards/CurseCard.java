package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public final class CurseCard extends EventCard {
    public CurseCard(String name, String description, CardTargetMode mode) {
        super(name, description, mode);
    }

    public void applyEffect(ArrayList<Player> targets) {
        for(final Player target : targets) {
            this.effect.effect(target);
        }
    }

    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }
}
