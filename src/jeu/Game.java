package src.jeu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class represents the game and also contains the methods to display it in a console environement
 */
public class Game {
    private static final int MAX_PLAYER_NUM = 6;
    private static final int MIN_PLAYER_NUM = 3;
    private final ArrayList<Player> players;
    private final ArrayDeque<Card> treasureCards;
    private final ArrayDeque<Card> eventCards;
    private final ArrayDeque<Card> discardPile;
    private Player currentPlayer;
    private final Random random;

    public Game(){
        players = new ArrayList<>();
        treasureCards = new ArrayDeque<>();
        eventCards = new ArrayDeque<>();
        discardPile = new ArrayDeque<>();
        random = new Random();
        currentPlayer = null;
    }

    public String getPlayerString(){
        StringBuffer sb = new StringBuffer();
        for(Player player : this.players){
            sb.append("- " + player.getName() + "\n");
        }
        return sb.toString();
    }

    private boolean playerAlreadyExists(String player_name){
        for(Player p : players){
            if(p.getName().equals(player_name)){
                return true;
            }
        }
        return false;
    }

    public int getPlayerNum(){
        return this.players.size();
    }

    /**
     * 
     * @param playerName
     * @throws Exception
     * @throws SamePlayerException
     * @throws InvalidPlayerNameException
     */
    public void addPlayer(String playerName) throws Exception, SamePlayerException, InvalidPlayerNameException{
        if(this.players.size() >= MAX_PLAYER_NUM){
            throw new Exception();
        }
        if(!this.isNameValid(playerName)){
            throw new InvalidPlayerNameException();
        }
        if(this.playerAlreadyExists(playerName)){
            throw new SamePlayerException();
        }
        this.players.add(new Player(playerName));
    }

    private void registerPlayers(){
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
            try{
                addPlayer(scan.nextLine());
            }
            catch(InvalidPlayerNameException invalidNameEx){
                System.out.println("The name you entered is invalid!");
            }
            catch(SamePlayerException spex){
                System.out.println("This name is already in use!");
            }
            catch(Exception ex){
                break;
            }
        }
        scan.close();
    }

    public void start(){
        System.out.println(this);
        this.currentPlayer = this.players.get(this.random.nextInt(this.getPlayerNum()));
        System.out.println("The first player is : " + this.currentPlayer.getName());
    }

    @Override
    public String toString(){
        StringBuffer out = new StringBuffer("There are " + this.players.size() + " players\n");
        for(Player p : this.players){
            out.append(p + "\n");
        }
        return out.toString();
    }

    private boolean isNameValid(String name){
        return name != null && name.matches("^[a-zA-Z0-9]+$");
    }

    public void discardCard(Card card){
        this.discardPile.add(card);
    }
}
