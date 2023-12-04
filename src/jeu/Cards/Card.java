package src.jeu.Cards;

import src.jeu.EffectFunction;
import src.jeu.Player;

public abstract class Card{
    protected final String name;
    protected final String description;
    protected EffectFunction effectFunction;

    public static final int TYPE_SELF = 0;
    public static final int TYPE_OTHER = 1;

    protected Card(String name, String description){
        this.name = name;
        this.description = description;
        this.effectFunction = null;
    }

    protected final void setEffectFunction(EffectFunction ef) {
        this.effectFunction = ef;
    }

    abstract public void applyEffect(Player target);

    abstract public void discard();

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }
}