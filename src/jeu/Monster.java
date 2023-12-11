package src.jeu;

import src.jeu.Cards.Evenementcard;

abstract class Monster extends Evenementcard {

    private int strenght;
    private int treasure;
    private int level;
    abstract void buff();
    abstract void defeat();
    
    Monster(String name,int strenght,int treasure,int level){
        super(name)
    
        this.strenght=strenght;
        this.treasure=treasure;
        this.level=level;

    }
}
