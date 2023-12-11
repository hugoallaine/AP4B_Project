package src.jeu.Cards;

abstract class MonsterCard extends EventCard {

    private int strenght;
    private int treasure;
    private int level;
    abstract void buff();
    abstract void defeat();
    
    MonsterCard(String name, String desc, CardTargetMode targetMode,int strenght,int treasure,int level){
        super(name, desc, targetMode);
    
        this.strenght=strenght;
        this.treasure=treasure;
        this.level=level;
    }
}
