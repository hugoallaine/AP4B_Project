package src.jeu;

import src.jeu.Cards.*;

/**
 * @brief Classe de définition des effets
 * @details Cette classe contient les effets qui peuvent être appliqués aux joueurs
 */
public class EffectsDefinitions {
    // IDs for effects
    public static final int LEVEL_DOWN = 1;
    public static final int DEATH = 2;
    public static final int REMOVE_CLASS = 3;
    public static final int REMOVE_LANGUAGE = 4;
    public static final int REMOVE_RANDOM_STUFF = 5;
    public static final int BUFF = 6;
    public static final int LEVEL_UP = 7;

    /**
     * @brief Fonction qui fait perdre un niveau au joueur
     * @param target Le joueur cible
     */
    public static void levelDown(Player target) {
        target.levelUp(-1);
    }

    /**
     * @brief Fonction qui tue le joueur
     * @param target Le joueur cible
     */
    public static void killTarget(Player target){
        for (Card card : target.getHand()){
            target.removeCardFromHand(card);
        }

        target.getStuff().forEach((k,card) -> {
            card = null;
        });
    }

    /**
     * @brief Fonction qui retire la classe du joueur
     * @param target Le joueur cible
     */
    public static void removeClass(Player target) {
        target.setClass(null);
    }

    /**
     * @brief Fonction qui retire la langue du joueur
     * @param target Le joueur cible
     */
    public static void removeLanguage(Player target) {
        target.setLanguage(null);
        
    }

    public static void removeRandomStuff(Player target) {
        target.removeRandomStuff();
    }

    /**
     * @brief Fonction qui retourne la fonction d'effet correspondant à l'ID
     * @param ID L'ID de l'effet
     * @return La fonction d'effet
     */
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
            case REMOVE_RANDOM_STUFF:
                return ((Player target) -> removeRandomStuff(target));
            case BUFF:
                return null;
            case LEVEL_UP:
                return ((Player target) -> target.levelUp(1));
            default:
                System.err.println("[ERROR] Invalid effect ID");
                return null;
        }
    } 
}