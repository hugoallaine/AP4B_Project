package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public final class StuffCard extends TreasureCard{
<<<<<<< Updated upstream
    final EquipementSlot equipementSlot;
    StuffCard(String name, String desc, CardTargetMode targetMode, EquipementSlot equipementSlot){
        super(name, desc, targetMode);
        this.equipementSlot = equipementSlot;
=======
    public int bonus;
    StuffCard(String name, String desc,int bonus, CardTargetMode targetMode){

        super(name, desc, targetMode);
        this.bonus=bonus;

>>>>>>> Stashed changes
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

    public int getBonus(){
        return bonus;
    }
    

    @Override
    public void discard() {
        
    }
}
