package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.GameClasses;
import src.jeu.Player;

public final class ClassCard extends EventCard{
    GameClasses gameClass;
    
    public ClassCard(String name, String desc, String className, CardTargetMode targetMode) {
        super(name, desc, targetMode);
        this.gameClass = GameClasses.getClassFromName(className);
        this.setEffectFunction((Player player) -> this.changePlayerClass(player));
    }

    @Override
    public void discard() {
        // TODO
    }

    @Override
    public void applyEffect(ArrayList<Player> targets) {
        for(Player target : targets){
            this.effectFunction.effect(target);
        }
    }

    private void changePlayerClass(Player p){
        p.setClass(this.gameClass);
    }
}
