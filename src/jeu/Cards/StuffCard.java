package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public final class StuffCard extends TreasureCard{
    final EquipementSlot equipementSlot;
    StuffCard(String name, String desc, CardTargetMode targetMode, EquipementSlot equipementSlot){
        super(name, desc, targetMode);
        this.equipementSlot = equipementSlot;
    }

    @Override
    public final void applyEffect(ArrayList<Player> targets) {
        for(Player target : targets){
            this.effectFunction.effect(target);
        }
    }

    @Override
    public void applyEffect(Player target) {
        this.effectFunction.effect(target);
    }
    

    @Override
    public void discard() {
        
    }
}
