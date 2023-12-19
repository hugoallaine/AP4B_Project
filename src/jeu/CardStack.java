package src.jeu;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Random;

import src.jeu.Cards.Card;

public class CardStack<T extends Card>{
    private final ArrayDeque<T> stack;
    CardStack() {
        this.stack = new ArrayDeque<>();
    }

    public T draw() throws NoSuchElementException {
        return this.stack.pop();
    }

    public void add(T card) {
        this.stack.add(card);
    }

    @SuppressWarnings("unchecked")
    public void shuffle() {
        Card[] cards = this.stack.toArray(new Card[0]); // Convertir le deque en tableau
        Random random = new Random();

        for (int i = cards.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);

            Card temp = cards[i];
            cards[i] = cards[randomIndex];
            cards[randomIndex] = temp;
        }

        this.stack.clear();
        for (Card card : cards) {
            this.add((T) card);
        }
        
    }

    @Override
    public String toString() {
        return this.stack.toString();
    }
}
