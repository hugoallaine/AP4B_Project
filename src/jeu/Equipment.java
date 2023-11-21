package src.jeu;

abstract class Equipment extends TreasureCard {
    private int stat;
    abstract void effect();
    private int getstat(){
        return stat;
    }

    
}
