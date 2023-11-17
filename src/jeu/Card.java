package src.jeu;

public abstract class Card{
    protected String name;
    protected String description;

    abstract void effect();
    abstract void discard();
}