package src.jeu.Cards;

public enum Languages {
    JAPANESE,
    ENGLISH,
    SPANISH,
    CHINESE,
    GERMAN,
    ITALIAN;
    
    @Override
    public String toString(){
        return this.name();
    }

    public static Languages getLanguageFromName(final String className){
        for(final Languages eth : Languages.values()){
            if(eth.name().equalsIgnoreCase(className)){
                return eth;
            }
        }
        return null;
    }
}
