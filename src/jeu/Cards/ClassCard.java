package src.jeu.Cards;

import src.jeu.GameClasses;
import src.jeu.Player;

public final class ClassCard extends EventCard{
    GameClasses gameClass;
    
    public ClassCard(String name, String desc, String className, CardTargetMode targetMode) {
        super(name, desc, targetMode);
        this.gameClass = GameClasses.getClassFromName(className);
        this.setEffect((Player player) -> this.changePlayerClass(player));
    }

    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }

    private void changePlayerClass(Player p){
        p.setClass(this.gameClass);
    }
}
