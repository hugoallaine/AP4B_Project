package src.jeu;
import src.jeu.Cards.*;

public class EffectsDefinitions {
    // IDs for effects
    public static final int LEVEL_DOWN = 1;
    public static final int DEATH = 2;
    public static final int REMOVE_CLASS = 3;
    public static final int REMOVE_LANGUAGE = 4;

    public static void levelDown(Player target) {
        target.levelUp(-1);
    }

    public static void killTarget(Player target){
        for (Card card : target.getHand()){
            target.removeCardFromHand(card);
        }

        target.getStuff().forEach((k,card) -> {
            card = null;
        });
    }

    public static void removeClass(Player target) {
        target.setClass(null);
    }

    public static void removeLanguage(Player target) {
        target.setLanguage(null);
        
    }

    public static EffectFunction getEffectFunctionFromID(int ID) {
        switch(ID) {
            case LEVEL_DOWN: 
                return ((Player target) -> levelDown(target));
            case DEATH:
                return ((Player target) -> killTarget(target));
            case REMOVE_CLASS:
                return ((Player target) -> removeClass(target));
            case REMOVE_LANGUAGE:
                return ((Player target) -> removeLanguage(target));
            default:
                return null;
        }
    }
    
}
