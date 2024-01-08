package src.jeu.Cards;

import src.jeu.EffectsDefinitions;
import src.jeu.Player;

/**
 * @brief Classe des cartes monstres (enfant de EventCard)
 * @details Les cartes monstres sont des cartes évènement qui représentent des monstres à combattre
 * 
 * @param strength La force du monstre (puissance)
 * @param treasure_amount Le nombre de trésors que le monstre donne
 * @param xp Le nombre d'xp que le monstre donne
 * @param buffs Le nombre de buffs que le monstre possède
 * @param passiveID L'ID de la passive du monstre
 */
public final class MonsterCard extends EventCard {
    private final int strength;
    private final int treasure_amount;
    private final int xp;
    private int buffs;
    private int passiveID;

    /**
     * @brief Constructeur de la classe MonsterCard
     * @param name Le nom de la carte
     * @param desc La description de la carte
     * @param strength La force du monstre (puissance)
     * @param treasure Le nombre de trésors que le monstre donne
     * @param xp Le nombre d'xp que le monstre donne
     * @param passiveID L'ID de la passive du monstre
     * @param onDeathEffectID L'ID de l'effet de mort du monstre
     */
    public MonsterCard(String name, String desc , int strength, int treasure, int xp, int passiveID, int onDeathEffectID){
        super(name, desc + "(base strength :" + strength + ")", CardTargetMode.SELF);
        assert strength > 0 && treasure > 0 && xp > 0;
        this.strength = strength;
        this.treasure_amount = treasure;
        this.xp = xp;
        this.passiveID = passiveID;
        this.buffs = 0;
        this.effect = EffectsDefinitions.getEffectFunctionFromID(onDeathEffectID);
    }

    /**
     * @brief Getter de la force du monstre
     * @return La force du monstre (puissance + buffs)
     */
    public int getStrength() {
        return this.strength + this.buffs;
    }

    /**
     * @brief Getter du nombre de trésors que le monstre donne
     * @return Le nombre de trésors que le monstre donne
     */
    public int getTreasureAmount() {
        return this.treasure_amount;
    }

    /**
     * @brief Getter du nombre de niveau que le monstre donne
     * @return Le nombre de niveau que le monstre donne
     */
    public int getXP() {
        return this.xp;
    }

    /**
     * @brief Getter de l'ID de la passive du monstre
     * @return L'ID de la passive du monstre
     */
    public int getPassive() {
        return this.passiveID;
    }

    /**
     * @brief Fonction qui augmente la force du monstre
     * @param powerBuff Le nombre de buffs à ajouter
     */
    public void buff(int powerBuff) {
        this.buffs += powerBuff;
    }

    /**
     * @brief Applique l'effet de la carte sur le joueur ciblé quand le monstre gagne
     * @param target Le joueur ciblé
     */
    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }

    /**
     * @brief Réinitialise les buffs du monstre
     */
    public void resetBuffs() {
        this.buffs = 0;
    }
}