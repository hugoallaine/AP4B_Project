package src.jeu.Cards;

import src.jeu.GameClasses;
import src.jeu.Player;

public final class ClassCard extends EventCard{
    GameClasses gameClass;
    
    public ClassCard(String name, String desc, String className) {
        super(name, desc);
        this.gameClass = GameClasses.getClassFromName(className);
        this.setEffectFunction((Player player) -> this.changePlayerClass(player));
    }

    @Override
    public void discard() {
        // TODO
    }

    @Override
    public void applyEffect(Player target) {
        this.effectFunction.effect(target);
    }

    private void changePlayerClass(Player p){
        p.setClass(this.gameClass);
    }
}
