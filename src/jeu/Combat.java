package src.jeu;

import java.util.NoSuchElementException;

import src.jeu.Cards.MonsterCard;
import src.jeu.Cards.MonsterPassives;

public class Combat {
    private final Player mainPlayer;
    private final MonsterCard mob;
    private final Game game;

    Combat(Player mainPlayer, MonsterCard mob, Game game) {
        this.mainPlayer = mainPlayer;
        this.mob = mob;
        this.game = game;
        MonsterPassives.applyPassive(mob, mainPlayer);
    }

    public void changeMonsterStats(int powerBuff) {
        this.mob.buff(powerBuff);
    }

    public MonsterCard gMonsterCard() {
        return this.mob;
    }

    public boolean fight() {
        System.out.println("Player str: " + mainPlayer.getPower());
        System.out.println("Monster str: " + mob.getStrength());
        if(mob.getStrength() <= mainPlayer.getPower()) {
            mainPlayer.levelUp(mob.getXP());
            this.distributeTreasures();
            return true;
        }
        mob.applyEffect(this.mainPlayer);
        return false;
    }

    public void distributeTreasures() {
        try{
            for(int i = 0; i < this.mob.getTreasureAmount(); i++) {
                this.mainPlayer.addCard(this.game.drawFromTreasureStack());
            }
        }catch(NoSuchElementException ex){
            System.out.println("[INFO] The treasure card stack is empty\n From fight() in Combat class");
        }
    }
}
