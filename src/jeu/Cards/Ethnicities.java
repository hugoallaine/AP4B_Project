package src.jeu.Cards;

// TODO
public enum Ethnicities {
    Japonais,Anglais,Espagnol,Chinois,Allemand,Italien;


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
