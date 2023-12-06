package src.jeu;

// TODO
public enum GameClasses {
    BARBARIAN("Barbarian"),
    ARCHER("Archer"),;
    
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
