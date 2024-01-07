package src.jeu.Cards;

import src.jeu.Player;

/**
 * @brief Classe représentant une carte de classe (permet de changer de classe)
 * @details Une carte de classe est caractérisée par un nom, une description et une classe
 * 
 * @param gameClass Classe de la carte
 */
public final class ClassCard extends EventCard{
    GameClasses gameClass;

    /**
     * @brief Constructeur de ClassCard
     * @param name Nom de la carte
     * @param desc Description de la carte
     * @param className Nom de la classe du joueur
     */
    public ClassCard(String name, String desc, String className) {
        super(name, desc, CardTargetMode.SELF);
        this.gameClass = GameClasses.getClassFromName(className);
        this.setEffect((Player player) -> this.changePlayerClass(player));
    }

    /**
     * @brief Applique l'effet de la carte
     * @param target Joueur cible
     */
    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }

    /**
     * @brief Change la classe du joueur
     * @param p Joueur cible
     */
    private void changePlayerClass(Player p){
        p.setClass(this.gameClass);
    }
}
