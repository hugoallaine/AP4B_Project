package src.jeu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static final int MAX_PLAYER_NUM = 6;
    private static final int MIN_PLAYER_NUM = 3;
    private final ArrayList<Player> players;
    private final ArrayDeque<Card> treasureCards;
    private final ArrayDeque<Card> eventCards;
    private final ArrayDeque<Card> discardPile;

    public Game(){
        players = new ArrayList<>();
        treasureCards = new ArrayDeque<>();
        eventCards = new ArrayDeque<>();
        discardPile = new ArrayDeque<>();
    }

    private void addPLayer(String playerName) throws Error{
        if(players.size() > MAX_PLAYER_NUM){
            throw new Error("There are already 6 players in the game, cannot add more");
        }
        this.players.add(new Player(playerName));
    }

    public void readFromConsole(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Munchkin, Please enter between 3 to 6 players");
        boolean startGame = false;
        while (this.players.size() < MAX_PLAYER_NUM) {
            if (this.players.size() >= MIN_PLAYER_NUM) {
                String ans = "";
                do {
                    System.out.println("There are enough players to start the game. Do you want to start now? [y/n]");
                    ans = scan.nextLine();
                    if (ans.equals("y")) {
                        startGame = true;
                        break;
                    }
                } while (!ans.equals("n"));

                if (startGame) {
                    break;
                }
            }
            System.out.println("Enter the player's name: ");
            addPLayer(scan.nextLine());
        }
        scan.close();
        this.start();
    }

    public void start(){
        System.out.println(this);
    }

    @Override
    public String toString(){
        String out = "There are " + this.players.size() + " players\n";
        for(Player p : players){
            out += p + "\n";
        }
        return out;
    }

    public void discardCard(Card card){
        this.discardPile.add(card);
    }
}
