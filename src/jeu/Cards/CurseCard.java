package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.EffectsDefinitions;
import src.jeu.Player;

/**
 * @brief Classe représentant une carte de malédiction (enfant de EventCard)
 * @details Une carte de malédiction est caractérisée par un nom, une description, un effet et un mode de ciblage
 */
public final class CurseCard extends EventCard {

    /**
     * @brief Constructeur de CurseCard
     * @param name Nom de la carte
     * @param description Description de la carte
     * @param id_effect Identifiant de l'effet de la carte
     * @param targetMode Mode de ciblage de la carte
     */
    public CurseCard(String name, String description, int id_effect, String targetMode) {
        super(name, description, CardTargetMode.getTargetModeFromString(targetMode));
        this.effect = EffectsDefinitions.getEffectFunctionFromID(id_effect);
    }

    /**
     * @brief Applique l'effet de la carte
     * @param targets Liste des joueurs cibles
     */
    public void applyEffect(ArrayList<Player> targets) {
        for(final Player target : targets) {
            this.effect.effect(target);
        }
    }

    /**
     * @brief Applique l'effet de la carte
     * @param target Joueur cible
     */
    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }
}
