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
        target.getStuff().forEach((k,card) -> {
            card = null;
        });
        

    }
    public static void class_loss(Player target) {
        target.setClass(null);
        
    }

    public static void ethnicity_loss(Player target) {
        target.setEthnicite(null);
        
    }
    
}
