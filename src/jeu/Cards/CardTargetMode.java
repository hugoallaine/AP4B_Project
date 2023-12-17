package src.jeu.Cards;

//TODO
public enum CardTargetMode {
    SELF,
    OTHER_PLAYER,
    EVERYONE,
    MONSTER,;


    public static CardTargetMode getTargeTModeFromString(final String tMode) {
        for(CardTargetMode c : CardTargetMode.values()){
            if(c.name().equals(tMode)){
                return c;
            }
        }
        return null;
    }
}