package src.jeu.Cards;

// TODO
public enum Ethnicities {
    JAPNESE("Japanese"),ENGLISH("English"),SPANISH("Spanish"),CHINESE("Chinese"),GERMAN("German"),ITALIAN("Italian");
    
private final String name;

    Ethnicities(String className){
        this.name = className;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public static Ethnicities getEthnicityFromName(final String className){
        for(final Ethnicities eth : Ethnicities.values()){
            if(eth.name().equalsIgnoreCase(className)){
                return eth;
            }
        }
        return null;
    }
}
