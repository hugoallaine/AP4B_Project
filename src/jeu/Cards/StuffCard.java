package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public final class StuffCard extends TreasureCard {

    //    final EquipementSlot equipementSlot;
    private int bonus;
    private EquipementSlot equipementSlot;

    public StuffCard(String name, String desc, int bonus, int id_passif, EquipementSlot equipementSlot, CardTargetMode targetMode) {
        super(name, desc, targetMode);
//        this.equipementSlot = equipementSlot;
        this.bonus = bonus;
        this.equipementSlot = equipementSlot;

    }


    @Override
    public final void applyEffect(ArrayList<Player> targets) {
        for (Player target : targets) {
            this.effectFunction.effect(target);
        }
    }

    public EquipementSlot getEquipementSlot() {
        return this.equipementSlot;
    }

    public void cheat_verification(Player target) {

        for (StuffCard stuff : target.getStuff()) {
            if (stuff.getEquipementSlot() == this.getEquipementSlot()) {
                stuff.discard();

            }
        }

    }

    @Override
    public void applyEffect(Player target) {
        cheat_verification(target);
        target.addStuff(this);
    }

    public int getBonus() {
        return bonus;
    }


    @Override
    public void discard() {

    }

}
