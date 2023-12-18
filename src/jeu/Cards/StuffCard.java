package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public final class StuffCard extends TreasureCard {

//    final EquipementSlot equipementSlot;
    private int bonus;

    public StuffCard(String name, String desc,int bonus,int id_passif, EquipementSlot equipementSlot ,CardTargetMode targetMode) {
        super(name, desc, targetMode);
//        this.equipementSlot = equipementSlot;
        this.bonus=bonus;

    }

    @Override
    public void applyEffect (Player target){
        this.effectFunction.effect(target);
    }

    public int getBonus () {
        return bonus;
    }


    @Override
    public void discard () {

    }

}
