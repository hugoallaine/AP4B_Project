package src.jeu;

// TODO
public enum Ethnicities {
    ELF("Elf"),;


    private final String ethString;
    Ethnicities(String ethnicity){
        this.ethString = ethnicity;
    }

    @Override
    public String toString(){
        return this.ethString;
    }

    public static Ethnicities getEthnicityFromName(String className){
        for(Ethnicities c : Ethnicities.values()){
            if(c.ethString.equals(className)){
                return c;
            }
        }
        return null;
    }
}
