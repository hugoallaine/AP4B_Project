package src.jeu.Cards;

import src.jeu.Player;

/**
 * @brief Classe des cartes à usage unique (enfant de TreasureCard)
 * @details Les cartes à usage unique sont des cartes trésors qui sont utilisées une seule fois
 * 
 * @param buff Le buff que la carte donne
 */
public final class SingleUseCard extends TreasureCard {
    int buff;
    
    /**
     * @brief Constructeur de la classe SingleUseCard
     * @param name Le nom de la carte
     * @param desc La description de la carte
     * @param buff Le buff que la carte donne
     * @param targetMode Le mode de ciblage de la carte
     * @param EffectID L'ID de l'effet de la carte
     */
    public SingleUseCard(String name, String desc, int buff, String targetMode){
        super(name, desc +" (Augmente la force de la cible de : "+buff+")", CardTargetMode.getTargetModeFromString(targetMode));
        this.buff = buff;
        this.effect = ((Player target) -> target.buff(this.buff));
    }

    /**
     * @brief Getter du buff que la carte donne
     * @return Le buff que la carte donne
     */
    public int getBuff() {
        return this.buff;
    }

    /**
     * @brief Applique l'effet de la carte à un joueur
     * @param target Le joueur ciblé par la carte
     */
    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }
}