package src.jeu.Cards;

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
