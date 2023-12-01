package src.jeu;

public abstract class Card{
    protected final String name;
    protected final String description;
    protected EffectFunction effectFunction;

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
}