package src.jeu.Cards;

import src.jeu.Player;

public abstract class Card {
    protected final String name;
    protected final String description;
    protected EffectFunction effect;
    protected final CardTargetMode mode;

    public final CardTargetMode getTargetMode() {
        return this.mode;
    }

    protected Card(String name, String description, CardTargetMode targetMode) {
        this.name = name;
        this.description = description;
        this.effect = null;
        this.mode = targetMode;
    }

    protected final void setEffect(EffectFunction f) {
        this.effect = f;
    }
    
    abstract public void applyEffect(Player target);


    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
}