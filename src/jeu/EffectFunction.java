package src.jeu;


// TODO: pour faire en sorte que les cartes puissent accepter des fonctions en arguments
// Afin de pouvoir définir les effets des cartes a part
// Faire gaffe a des potentiels problemes
@FunctionalInterface
public interface EffectFunction {
    public abstract void effect(Player target);
}
