package src.jeu.Cards;

abstract public class EventCard extends Card {
    protected EventCard(String name, String desc, CardTargetMode targetMode){
        super(name, desc, targetMode);
    }
}
