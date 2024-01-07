package src.jeu.Cards;

import src.jeu.Player;

/**
 * @brief Classe abstraite représentant une carte
 * @details Une carte est caractérisée par un nom, une description, un effet et un mode de ciblage
 * 
 * @param name Nom de la carte
 * @param description Description de la carte
 * @param effect Effet de la carte
 * @param mode Mode de ciblage de la carte
 */
public abstract class Card {
    protected final String name;
    protected final String description;
    protected EffectFunction effect;
    protected final CardTargetMode mode;

    /**
     * @brief Constructeur de la classe Card
     * @param name Nom de la carte
     * @param description Description de la carte
     * @param targetMode Mode de ciblage de la carte
     */
    protected Card(String name, String description, CardTargetMode targetMode) {
        this.name = name;
        this.description = description;
        this.effect = null;
        this.mode = targetMode;
    }

    /**
     * @brief Getter du nom de la carte
     * @return Le nom de la carte
     */
    public String getName() {
        return this.name;
    }

    /**
     * @brief Getter de la description de la carte
     * @return La description de la carte
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @brief Getter du mode de ciblage de la carte
     * @return Le mode de ciblage de la carte
     */
    public final CardTargetMode getTargetMode() {
        return this.mode;
    }

    /**
     * @brief Setter de l'effet de la carte
     * @param f L'effet de la carte
     */
    public final void setEffect(EffectFunction f) {
        this.effect = f;
    }
    
    /**
     * @brief Fonction abstraite d'application de l'effet de la carte sur un joueur
     * @param target Joueur ciblé par la carte
     */
    abstract public void applyEffect(Player target);

    /**
     * @brief Sortie String de la classe Card
     * @return Le nom de la carte
     */
    public String toString() {
        return this.name;
    }
}