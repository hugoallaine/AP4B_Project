package src.jeu;

import java.util.ArrayDeque;

public class CardStack<T extends Card>{
    private final ArrayDeque<T> stack;
    CardStack() {
        this.stack = new ArrayDeque<>();
    }

    public T draw(){
        return this.stack.pop();
    }

    public void add(T card){
        this.stack.add(card);
    }
}
