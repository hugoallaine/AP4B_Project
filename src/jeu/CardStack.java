package src.jeu;

import java.util.ArrayDeque;
import java.util.Random;

import src.jeu.Cards.Card;

public class CardStack<T extends Card>{
    private final ArrayDeque<T> stack;
    CardStack() {
        this.stack = new ArrayDeque<>();
    }

    public T draw() {
        return this.stack.pop();
    }

    public void add(T card) {
        this.stack.add(card);
    }

    @SuppressWarnings("unchecked")
    public void shuffle() {
        Card[] cartesArray = this.stack.toArray(new Card[0]); // Convertir le deque en tableau
        Random random = new Random();

        for (int i = cartesArray.length - 1; i > 0; i--) {
            int indexAleatoire = random.nextInt(i + 1);

            Card temp = cartesArray[i];
            cartesArray[i] = cartesArray[indexAleatoire];
            cartesArray[indexAleatoire] = temp;
        }

        // Mettre à jour le deque avec le tableau mélangé
        // Un peu dégueulasse comme solution, surtout le cast
        this.stack.clear();
        for (Card carte : cartesArray) {
            
            this.add((T) carte);
        }
        
    }
}
