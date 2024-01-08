package src.jeu;

import java.util.NoSuchElementException;

import src.jeu.Cards.MonsterCard;
import src.jeu.Cards.MonsterPassives;

/**
 * @brief Classe de combat
 * @details Classe qui gère le combat entre le joueur et le monstre
 * 
 * @param mainPlayer Le joueur principal
 * @param mob Le monstre
 * @param game Le jeu
 */
public class Combat {
    private final Player mainPlayer;
    private final MonsterCard mob;
    private final Game game;

    /**
     * @brief Constructeur de la classe Combat
     * @param mainPlayer Le joueur principal
     * @param mob Le monstre
     * @param game Le jeu
     */
    Combat(Player mainPlayer, MonsterCard mob, Game game) {
        this.mainPlayer = mainPlayer;
        this.mob = mob;
        this.game = game;
        MonsterPassives.applyPassive(mob, mainPlayer);
    }

    /**
     * @brief Fonction qui change les statistiques du monstre
     * @param powerBuff Le buff de puissance
     */
    public void changeMonsterStats(int powerBuff) {
        this.mob.buff(powerBuff);
    }

    /**
     * @brief Fonction qui retourne le monstre
     * @return Le monstre
     */
    public MonsterCard gMonsterCard() {
        return this.mob;
    }

    /**
     * @brief Fonction qui calcule le résultat du combat
     * @return true si le joueur gagne, false sinon
     */
    public boolean fight() {
        if(mob.getStrength() <= mainPlayer.getPower()) {
            this.mainPlayer.levelUp(this.mob.getXP());
            this.distributeTreasures();
            this.mob.resetBuffs();
            return true;
        }
        mob.applyEffect(this.mainPlayer);
        return false;
    }

    /**
     * @brief Fonction qui distribue les trésors au joueur s'il gagne
     */
    public void distributeTreasures() {
        try {
            for (int i = 0; i < this.mob.getTreasureAmount(); i++) {
                this.mainPlayer.addCard(this.game.drawFromTreasureStack());
            }
        } catch(NoSuchElementException ex) {
            System.out.println("[INFO] The treasure card stack is empty\n From fight() in Combat class");
        }
    }
}