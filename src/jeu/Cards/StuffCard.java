package src.jeu.Cards;

import src.jeu.Player;

/**
 * @brief Classe des cartes équipements (enfant de TreasureCard)
 * @details Les cartes équipements sont des cartes trésors qui sont équipées par le joueur
 * 
 * @param equipementSlot L'emplacement de l'équipement
 * @param bonus Le bonus que l'équipement donne
 */
public final class StuffCard extends TreasureCard {
    private final EquipementSlot equipementSlot;
    private int bonus;
    // private EquipementSlot equipementSlot;

    /**
     * @brief Constructeur de la classe StuffCard
     * @param name Le nom de la carte
     * @param desc La description de la carte
     * @param bonus Le bonus que l'équipement donne
     * @param id_passif L'ID de la passive de l'équipement
     * @param equipementSlot L'emplacement de l'équipement
     */
    public StuffCard(String name, String desc, int bonus, String equipementSlot) {
        super(name, desc + " (+"+ bonus +" puissance)", CardTargetMode.SELF);
        this.bonus = bonus;
        this.equipementSlot = EquipementSlot.getFromString(equipementSlot);
    }

    /**
     * @brief Getter du bonus que l'équipement donne
     * @return Le bonus que l'équipement donne
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * @brief Getter de l'emplacement de l'équipement
     * @return L'emplacement de l'équipement
     */
    public EquipementSlot getEquipementSlot() {
        return this.equipementSlot;
    }

    /**
     * @brief Applique l'effet de la carte à un joueur
     * @param target Le joueur ciblé par la carte
     */
    @Override
    public void applyEffect(Player target) {
        target.addStuff(this);
    }
}