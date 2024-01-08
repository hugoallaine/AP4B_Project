package src.jeu;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Random;

import src.jeu.Cards.Card;

/**
 * @brief Classe de pile de cartes
 * 
 * @param stack La pile de cartes
 */
public class CardStack<T extends Card>{
    private final ArrayDeque<T> stack;
    
    /**
     * @brief Constructeur de la pile de cartes
     */
    CardStack() {
        this.stack = new ArrayDeque<>();
    }

    /**
     * @brief Fonction qui retire la carte du dessus de la pile
     * @return La carte du dessus de la pile
     * @throws NoSuchElementException Si la pile est vide
     */
    public T draw() throws NoSuchElementException {
        return this.stack.pop();
    }

    /**
     * @brief Fonction qui ajoute une carte à la pile
     * @param card La carte à ajouter
     */
    public void add(T card) {
        this.stack.add(card);
    }

    /**
     * @brief Fonction qui mélange la pile
     */
    @SuppressWarnings("unchecked")
    public void shuffle() {
        Card[] cards = this.stack.toArray(new Card[0]);
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

    /**
     * @brief Sortie console de la classe
     * @return Une représentation de la pile de cartes sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        return this.stack.toString();
    }
}
