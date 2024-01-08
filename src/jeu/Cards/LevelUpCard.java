package src.jeu.Cards;
import src.jeu.Player;

public class LevelUpCard extends TreasureCard {

    /**
     * @brief Constructeur de la classe LevelUpCard
     * @param name Le nom de la carte
     * @param desc La description de la carte
     */
    public LevelUpCard(String name, String desc) {
        super(name, desc, CardTargetMode.SELF);
        this.effect = ((Player target) -> target.levelUp(1));
    }
    @Override
    public void applyEffect(Player target) {
        target.levelUp(1);
    }
}
