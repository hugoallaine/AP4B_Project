package src.jeu;

public abstract class Card{
    protected String name;
    protected String description;
    protected Game game;

    Card(String name, String description, Game game){
        this.name = name;
        this.description = description;
        this.game = game;
    }

    abstract public void effect();

    abstract public void discard();
}