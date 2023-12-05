package src.jeu.Cards;

import java.util.ArrayList;

import src.jeu.Player;

public abstract class Card{
    protected final String name;
    protected final String description;
    protected EffectFunction effectFunction;
    protected CardTargetMode mode;

    public CardTargetMode getTargetMode() {
        return this.mode;
    }

    protected Card(String name, String description, CardTargetMode targetMode){
        this.name = name;
        this.description = description;
        this.effectFunction = null;
        this.mode = targetMode;
    }

    protected final void setEffectFunction(EffectFunction ef) {
        this.effectFunction = ef;
    }

    abstract public void applyEffect(ArrayList<Player> targets);

    abstract public void discard();

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }
}