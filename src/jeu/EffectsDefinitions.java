package src.jeu;
import src.jeu.Cards.*;

public class EffectsDefinitions {
    public static void levelDown(Player target) {
        target.levelUp(-1);
    }
    public static void death(Player target){
        for (Card card : target.getHand()){
            target.removeCardFromHand(card);
        }
        for (StuffCard card : target.getStuff()){
            target.removeStuff(card);
        }
        target.getHasDrawn()

    }
    
}
