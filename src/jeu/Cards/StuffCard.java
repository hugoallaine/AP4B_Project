package src.jeu.Cards;

import src.jeu.Player;

public final class StuffCard extends TreasureCard {

    private final EquipementSlot equipementSlot;
    private int bonus;
    // private EquipementSlot equipementSlot;

    public StuffCard(String name, String desc, int bonus, int id_passif, String equipementSlot) {
        super(name, desc + "\n(+"+ bonus +" power)", CardTargetMode.SELF);
        this.bonus = bonus;
        this.equipementSlot = EquipementSlot.getFromString(equipementSlot);
    }

    public EquipementSlot getEquipementSlot() {
        return this.equipementSlot;
    }

    @Override
    public void applyEffect(Player target) {
        target.addStuff(this);
    }

    public int getBonus() {
        return bonus;
    }
}
