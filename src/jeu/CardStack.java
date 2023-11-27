package src.jeu;

import java.util.Stack;

public class CardStack{
    private final Stack<Card> stack;
    CardStack(){
        this.stack = new Stack<>();
    }

    public Card draw(){
        return this.stack.pop();
    }

    public void add(Card card){
        this.stack.add(card);
    }
}
