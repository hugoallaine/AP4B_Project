package src.jeu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private ArrayDeque<Card> treasureCards;
    private ArrayDeque<Card> eventCards;
    private ArrayDeque<Card> discardPile;

    public Game(){
        players = new ArrayList<>();
    }

    public void addPLayer(String playerName){
        if(players.size() > 6){
            return;
        }
        this.players.add(new Player(playerName));
    }

    public void readFromConsole(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Munchkin, Please enter between 3 to 6 players");
        boolean startGame = false;
        while (this.players.size() < 6) {
            if (this.players.size() >= 3) {
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
