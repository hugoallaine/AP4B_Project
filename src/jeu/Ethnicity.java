package src.jeu;

// TODO
public enum Ethnicity {
    ELF("Elf"),;


    private final String ethString;
    Ethnicity(String ethnicity){
        this.ethString = ethnicity;
    }

    @Override
    public String toString(){
        return this.ethString;
    }

    public static Ethnicity getClassFromName(String className){
        for(Ethnicity c : Ethnicity.values()){
            if(c.ethString.equals(className)){
                return c;
            }
        }
        return null;
    }
}
