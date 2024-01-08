package src.jeu.Cards;

/**
 * @brief Classe abstraite des cartes trésor
 * @details Les cartes trésors sont des cartes qui peuvent être gagnées par le joueur
 */
public abstract class TreasureCard extends Card{

    /**
     * @brief Constructeur de la classe TreasureCard
     * @param name Le nom de la carte
     * @param desc La description de la carte
     * @param targetMode Le mode de ciblage de la carte
     */
    protected TreasureCard(String name, String desc, CardTargetMode targetMode){
        super(name, desc, targetMode);
    }   
}