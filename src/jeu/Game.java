package src.jeu;

import java.util.*;

import src.jeu.Cards.*;
import src.jeu.Exceptions.*;

/**
 * This class represents the game and also contains the methods to display it in a console environement
 */
public final class Game {
    private static final int MAX_PLAYER_NUM = 6;
    public static final int MIN_PLAYER_NUM = 3;
    public static final int MAX_CARD_IN_HAND = 5;

    private final ArrayList<Player> players;
    private final CardStack<TreasureCard> treasureCards;
    private final CardStack<EventCard> eventCards;
    private final CardStack<Card> discardPile;
    private Player currentPlayer;
    private final Random random;

    public Game() {
        players = new ArrayList<>();
        treasureCards = new CardStack<>();
        eventCards = new CardStack<>();
        discardPile = new CardStack<>();
        random = new Random();
        currentPlayer = null;


        this.createCards();
        // Thread pour créer les cartes pendant qu'on lance le jeu

        // new Runnable() {
        //     @Override
        //     public void run() {
        //         createCards();
        //     }
        // }.run();
    }

    public String getPlayerString() {
        StringBuffer sb = new StringBuffer();
        for (Player player : this.players) {
            sb.append("- " + player.getName() + "\n");
        }
        return sb.toString();
    }

    private boolean playerAlreadyExists(final String player_name) {
        for (Player p : players) {
            if (p.getName().equals(player_name)) {
                return true;
            }
        }
        return false;
    }

    public int getPlayerNum() {
        return this.players.size();
    }

    /**
     * @param playerName
     * @throws TooManyPlayersException
     * @throws SamePlayerException
     * @throws InvalidPlayerNameException
     */
    public void addPlayer(final String playerName) throws TooManyPlayersException, SamePlayerException, InvalidPlayerNameException {
        if (this.players.size() >= MAX_PLAYER_NUM) {
            throw new TooManyPlayersException();
        }
        if (!this.isNameValid(playerName)) {
            throw new InvalidPlayerNameException();
        }
        if (this.playerAlreadyExists(playerName)) {
            throw new SamePlayerException();
        }
        this.players.add(new Player(playerName));
    }

    //TODO: bouger ça dans une classe pour le terminal si on en fait une
    // private void registerPlayers(){
    //     Scanner scan = new Scanner(System.in);
    //     System.out.println("Welcome to Munchkin, Please enter between 3 to 6 players");
    //     boolean startGame = false;
    //     while (this.players.size() < MAX_PLAYER_NUM) {
    //         if (this.players.size() >= MIN_PLAYER_NUM) {
    //             String ans = "";
    //             do {
    //                 System.out.println("There are enough players to start the game. Do you want to start now? [y/n]");
    //                 ans = scan.nextLine();
    //                 if (ans.equalsIgnoreCase("y")) {
    //                     startGame = true;
    //                     break;
    //                 }
    //             } while (!ans.equalsIgnoreCase("n"));

    //             if (startGame) {
    //                 break;
    //             }
    //         }
    //         System.out.println("Enter the player's name: ");
    //         try{
    //             addPlayer(scan.nextLine());
    //         }
    //         catch(InvalidPlayerNameException invalidNameEx) {
    //             System.out.println("The name you entered is invalid!");
    //         }
    //         catch(SamePlayerException spex) {
    //             System.out.println("This name is already in use!");
    //         }
    //         catch(Exception ex){
    //             break;
    //         }
    //     }
    //     scan.close();
    // }

    public void start() {
        this.createCards();
        this.eventCards.shuffle();
        this.treasureCards.shuffle();
        this.distributeCards();
        this.currentPlayer = this.players.get(this.random.nextInt(this.getPlayerNum()));
    }

    private void distributeCards() {
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                player.addCard(treasureCards.draw());
                player.addCard(eventCards.draw());
            }
        }
    }

    public Player isGameFinsihed() {
        for (final Player player : players) {
            if (player.getLevel() >= 10) {
                System.out.println("Game should be finished");
                return player;
            }
        }
        return null;
    }

    private void createCards() {


        List<String[]> cardData = CSVFileReader.readCSV("cards.csv");
        for (String[] card : cardData) {
            if (card[0].equals("1")) {
                this.eventCards.add(new MonsterCard(card[1], card[2], Integer.parseInt(card[3]), Integer.parseInt(card[4]), Integer.parseInt(card[5]), Integer.parseInt(card[6]), Integer.parseInt(card[7])));
            } else if ((Objects.equals(card[0], "10"))) {
                this.treasureCards.add(new SingleUseCard(card[1], card[2], Integer.parseInt(card[3]), Integer.parseInt((card[4])), CardTargetMode.SELF));
            } else if (Objects.equals(card[0], "20")) {
                this.treasureCards.add(new StuffCard(card[1], card[2], Integer.parseInt(card[3]), Integer.parseInt((card[4])), EquipementSlot.NONE, CardTargetMode.SELF));
            }

        }

        for (int i = 0; i < 80; i++) {
            this.eventCards.add(new ClassCard("Barbarian", "Description", "Barbarian", CardTargetMode.SELF));
//            this.treasureCards.add(new XpCard("LevelUp", "Desc", 1, 0, CardTargetMode.SELF));
            this.treasureCards.add(new StuffCard("Sword", "Desc", 1, 0, EquipementSlot.NONE, CardTargetMode.SELF));
            this.treasureCards.add(new StuffCard("pistolet", "Desc", 1, 0, EquipementSlot.NONE, CardTargetMode.SELF));

        }

    }

    /**
     * If the current player is able to change, we set the currentPlayer to be the next player in the list and set {@code hasDrawn}
     * on the previous player to false
     *
     * @throws TooManyCardsInHandException
     * @throws PlayerMustDrawException
     */
    public void nextTurn() throws TooManyCardsInHandException, PlayerMustDrawException {
        if (this.canFinishTurn()) {
            int currentPlayerIndex = this.players.indexOf(this.currentPlayer);
            this.currentPlayer.setHasDrawn(false);
            this.currentPlayer = this.players.get((currentPlayerIndex + 1) % this.players.size());
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("There are " + this.players.size() + " players\n");
        for (Player p : this.players) {
            out.append(p + "\n");
        }
        return out.toString();
    }

    private boolean isNameValid(String name) {
        return name != null && name.matches("^[a-zA-Z0-9]+$");
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Draws a card from the event stack and sets the {@code hasDrawn} attribute of the current player to true
     *
     * @return The card drawn by the player
     * @throws NoSuchElementException
     */
    public EventCard drawFromEventStack() throws NoSuchElementException {
        this.currentPlayer.setHasDrawn(true);
        EventCard cardDrawn = this.eventCards.draw();
        return cardDrawn;
    }

    public TreasureCard drawFromTreasureStack() throws NoSuchElementException {
        return this.treasureCards.draw();
    }

    public boolean canFinishTurn() throws TooManyCardsInHandException, PlayerMustDrawException {
        if (!this.currentPlayer.getHasDrawn()) {
            throw new PlayerMustDrawException();
        }
        if (this.currentPlayer.getHand().size() > Game.MAX_CARD_IN_HAND) {
            throw new TooManyCardsInHandException();
        }
        return true;
    }

    public void discard(Card card) {
        this.discardPile.add(card);
    }

    public void applyCurseEffect(CurseCard card) {
        switch (card.getTargetMode()) {
            case SELF:
                card.applyEffect(this.currentPlayer);
                break;
            case OTHER_PLAYER:
                //TODO
            case EVERYONE:
                card.applyEffect(this.players);
            default:
                System.err.println("[ERROR] Should be unreachable");
                assert false;
        }
    }

    public Combat startCombat(Player player, MonsterCard monster, List<Card> effectCards) {
        final Combat combat = new Combat(this.currentPlayer, monster, this);
        for (final Card card : effectCards) {
            //TODO: Check si la carte peut affecter les monstres ou le joueur   
            combat.changeMonsterStats(1);
        }
        return combat;
    }

}
