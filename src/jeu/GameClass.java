package src.jeu;

// TODO
public enum GameClass {
    BARBARIAN("Barbarian"),
    ARCHER("Archer"),;
    
    private final String name;

    GameClass(String className){
        this.name = className;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public static GameClass getClassFromName(String className){
        for(GameClass c : GameClass.values()){
            if(c.name.equals(className)){
                return c;
            }
        }
        return null;
    }
}
