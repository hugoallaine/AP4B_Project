package src.jeu.Cards;

import java.util.Random;

/**
 * @brief Enumération des emplacements d'équipement
 * @details Les emplacements d'équipement sont les suivants : arme, torse, jambes, gants, casque
 */
public enum EquipementSlot {
    WEAPON,
    TORSO,
    LEGS,
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

    public static EquipementSlot getRandom() {
        Random rand = new Random();
        return EquipementSlot.values()[rand.nextInt(size)];
    }
}
