package src.jeu;

abstract class levelupcard extends TreasureCard{
private int gain_level;
levelupcard(int i){
    gain_level=i;
}
void play(Player user,Player target){
    target.level_up(gain_level);
    
}
boolean verification(Player user,Player target){
    if (target.getlevel()==9){
        return false;

    }
    else{
        return true;
    }
}
    
}
