package src.jeu.Cards;

//TODO
/**
 * @brief Enumération des modes de ciblage des cartes
 * @details Les modes de ciblage sont les suivants :
 * SELF: La carte est utilisée sur le joueur qui la joue
 * OTHER_PLAYER: La carte est utilisée sur un autre joueur
 * EVERYONE: La carte est utilisée sur tous les joueurs
 * MONSTER_OR_PLAYER: La carte est utilisée sur un monstre ou un joueur
 */
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