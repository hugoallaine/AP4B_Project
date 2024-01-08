package src.jeu.Cards;

/**
 * @brief Enumération des classes de jeu
 * @details Les classes de jeu sont les suivantes :
 * - INFO
 * - IMSI
 * - GMC
 * - ENERGY
 * - EDIM
 */
public enum GameClasses {
    INFO,
    IMSI,
    GMC,
    ENERGY,
    EDIM,;

    @Override
    public String toString(){
        return this.name();
    }

    public static GameClasses getClassFromName(String className){
        for(GameClasses c : GameClasses.values()){
            if(c.name().equalsIgnoreCase(className)){
                return c;
            }
        }
        return null;
    }
}
