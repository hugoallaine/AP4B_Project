package src.jeu;

// TODO
public enum GameClasses {
    INFO("Info"),
    IMSI("Imsi"),
    GMC("Gmc"),
    ENERGY("Energy"),
    EDIM("Edim"),;
    
    private final String name;

    GameClasses(String className){
        this.name = className;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public static GameClasses getClassFromName(String className){
        for(GameClasses c : GameClasses.values()){
            if(c.name.equalsIgnoreCase(className)){
                return c;
            }
        }
        return null;
    }
}
