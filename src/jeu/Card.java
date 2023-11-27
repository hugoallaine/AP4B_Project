package src.jeu;

public abstract class Card{
    protected String name;
    protected String description;

    protected Card(String name, String description){
        this.name = name;
        this.description = description;
    }

    abstract public void effect();

    abstract public void discard();
}