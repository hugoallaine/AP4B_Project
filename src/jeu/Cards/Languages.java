package src.jeu.Cards;

/**
 * @brief Enumération des cartes langue
 * @details Les langues sont les suivantes : Japonais, Anglais, Espagnol, Chinois, Allemand, Italien
 */
public enum Languages {
    JAPONAIS,
    ANGLAIS,
    ESPAGNOL,
    CHINOIS,
    ALLEMAND,
    ITALIEN;
    
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