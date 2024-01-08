package src.jeu.Cards;

import src.jeu.Player;

// pour faire en sorte que les cartes puissent accepter des fonctions en arguments
// Afin de pouvoir définir les effets des cartes a part
// Faire gaffe a des potentiels problemes

/**
 * @brief Interface fonctionnelle pour les effets des cartes
 * @details Cette interface permet de définir des fonctions qui prennent en paramètre un joueur
 */
@FunctionalInterface
public interface EffectFunction {
    public abstract void effect(Player target);
}