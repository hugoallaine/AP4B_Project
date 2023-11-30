package src.jeu;

public abstract class Levelupcard extends TreasureCard {
    private int gain_level;

    Levelupcard(int i){
        gain_level=i;
    }

    public void effect(Player target){
        target.level_up(gain_level);
    }

    public boolean verification(Player user,Player target){
        return target.getlevel() == 9;
    }
    
}
