package src.jeu.Cards;

/**
 * @brief Classe abstraite des cartes évènement (enfant de Card)
 * @details Les cartes évènement sont des cartes qui ont un effet immédiat sur le jeu / les joueurs
 */
public abstract class EventCard extends Card {

    /**
     * @brief Constructeur de la classe EventCard
     * @param name Nom de la carte
     * @param desc Description de la carte
     * @param targetMode Mode de ciblage de la carte
     */
    protected EventCard(String name, String desc, CardTargetMode targetMode){
        super(name, desc, targetMode);
    }
}