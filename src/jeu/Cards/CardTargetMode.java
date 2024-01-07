package src.jeu.Cards;

//TODO
public enum CardTargetMode {
    SELF,
    OTHER_PLAYER,
    EVERYONE,
    MONSTER_OR_PLAYER,;


    public static CardTargetMode getTargetModeFromString(final String tMode) {
        for(CardTargetMode c : CardTargetMode.values()){
            if(c.name().equals(tMode)){
                return c;
            }
        }
        return null;
    }
}