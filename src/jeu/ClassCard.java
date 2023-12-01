package src.jeu;

public final class ClassCard extends EventCard{
    GameClasses gameClass;
    
    public ClassCard(String className) {
        super();
        this.setEffectFunction((player) -> this.changePlayerClass(player));
        this.gameClass = GameClasses.getClassFromName(className);
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
