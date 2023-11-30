package src.jeu;

public final class ClassCard extends EventCard{
    GameClass gameClass;
    
    public ClassCard(String className) {
        this.gameClass = GameClass.getClassFromName(className);
    }

    @Override
    public void discard() {
        // TODO
    }

    @Override
    public void effect(Player target) {
        target.setClass(this.gameClass);
    }
}
