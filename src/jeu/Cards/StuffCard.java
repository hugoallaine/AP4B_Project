package src.jeu.Cards;

import src.jeu.Player;

public final class StuffCard extends TreasureCard {

    private final EquipementSlot equipementSlot;
    private int bonus;
    // private EquipementSlot equipementSlot;

    public StuffCard(String name, String desc, int bonus, int id_passif, EquipementSlot equipementSlot, CardTargetMode targetMode) {
        super(name, desc, targetMode);
        this.bonus = bonus;
        this.equipementSlot = equipementSlot;

    }

    public EquipementSlot getEquipementSlot() {
        return this.equipementSlot;
    }

    public void canTargetEquip(Player target) {
        for (final StuffCard stuff : target.getStuff()) {
            if (stuff.equipementSlot == this.equipementSlot) {
                target.removeStuff(stuff);
            }
        }
    }

    @Override
    public void applyEffect(Player target) {
        this.canTargetEquip(target);
        target.addStuff(this);
    }

    public int getBonus() {
        return bonus;
    }
}
