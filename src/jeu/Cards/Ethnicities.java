package src.jeu.Cards;

// TODO
public enum Ethnicities {
    ELF,;


    @Override
    public String toString(){
        return this.name();
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
