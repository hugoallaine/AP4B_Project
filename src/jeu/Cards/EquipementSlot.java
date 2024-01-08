package src.jeu.Cards;

/**
 * @brief Enumération des emplacements d'équipement
 * @details Les emplacements d'équipement sont les suivants : arme, torse, jambes, gants, casque
 */
public enum EquipementSlot {
    WEAPON,
    TORSO,
    LEGS,
    GLOVES,
    HELMET,;

    public static int size = EquipementSlot.values().length;
    
    public static EquipementSlot getFromString(String equipementSlot) {
        for(final EquipementSlot eq : EquipementSlot.values()) {
            if( eq.name().equalsIgnoreCase(equipementSlot)) {
                return eq;
            }
        }
        return null;
    }
}
