package src.jeu.Cards;

import src.jeu.Player;

/**
 * @brief Classe des passives des monstres
 * @details Les passives des monstres sont des effets d'équilibrage qui sont appliqués à un monstre
 */
public class MonsterPassives {
    public static final int weak_Japanese = 1;
    public static final int strong_Japanese = 2;
    public static final int weak_English = 3;
    public static final int strong_English = 4;
    public static final int weak_Spanish = 5;
    public static final int strong_Spanish = 6;
    public static final int weak_Chinese = 7;
    public static final int strong_Chinese = 8;
    public static final int weak_German = 9;
    public static final int strong_German = 10;
    public static final int weak_Italian = 11;
    public static final int strong_Italian = 12;

    public static void weak_Japanese(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.JAPONAIS){
            monster.buff(-5);
        }
    }
    public static void strong_Japanese(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.JAPONAIS){
            monster.buff(+5);
        }
    }
    public static void weak_English(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.ANGLAIS){
            monster.buff(-5);
        }
    }
    public static void strong_English(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.ANGLAIS){
            monster.buff(+5);
        }
    }
    public static void weak_Spanish(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.ESPAGNOL){
            monster.buff(-5);
        }
    }
    public static void strong_Spanish(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.ESPAGNOL){
            monster.buff(+5);
        }
    }
    public static void weak_Chinese(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.CHINOIS){
            monster.buff(-5);
        }
    }
    public static void strong_Chinese(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.CHINOIS){
            monster.buff(+5);
        }
    }
    public static void weak_German(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.ALLEMAND){
            monster.buff(-5);
        }
    }
    public static void strong_German(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.ALLEMAND){
            monster.buff(+5);
        }
    }
    public static void weak_Italian(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.ITALIEN){
            monster.buff(-5);
        }
    }
    public static void strong_Italian(MonsterCard monster ,Player target){
        if (target.getLanguage() == Languages.ITALIEN){
            monster.buff(+5);
        }
    }

    /**
     * @brief Applique le buff sur le monstre en fonction de la langue du joueur attaquant
     * @param target Le monstre ciblé
     * @param fighter Le joueur ciblé
     */
    public static void applyPassive(MonsterCard target, Player fighter) {
        switch(target.getPassive()) {
            case weak_Japanese:
                weak_Japanese(target, fighter);
                break;
            case strong_Japanese:
                strong_Japanese(target, fighter);
                break;
            case weak_English:
                weak_English(target, fighter);
                break;
            case strong_English:
                strong_English(target, fighter);
                break;
            case weak_Spanish:
                weak_Spanish(target, fighter);
                break;
            case strong_Spanish:    
                strong_Spanish(target, fighter);
                break;
            case weak_Chinese:
                weak_Chinese(target, fighter);
                break;
            case strong_Chinese:
                strong_Chinese(target, fighter);
                break;
            case weak_German:
                weak_German(target, fighter);
                break;
            case strong_German:
                strong_German(target, fighter);
                break;
            case weak_Italian:
                weak_Italian(target, fighter);
                break;
            case strong_Italian:
                strong_Italian(target, fighter);
                break;
        }
    }
                
}