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
    private static final int MAX_LEVEL = 10;

    private static final int MONSTER_CARD = 1;
    private static final int SINGLE_USE_CARD = 10;
    private static final int STUFF_CARD = 20;
    private static final int CLASS_CARD = 30;
    private static final int LANGUAGE_CARD = 40;
    private static final int CURSE_CARD = 50;

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

        // Thread pour créer les cartes pendant qu'on lance le jeu
        new Runnable() {
            @Override
            public void run() {
                createCards();
            }
        }.run();
    }

    public String getPlayerString() {
        StringBuffer sb = new StringBuffer();
        for (Player player : this.players) {
            sb.append("- " + player.getName() + "\n");
        }
        return sb.toString();
    }

    private boolean playerAlreadyExists(final String player_name) {
        for (final Player p : players) {
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
     * Adds a player to the list of player while also making sure that there isn't a player with a similar name, or that the game isn't already full,
     * the cap being : {@code Game.MAX_PLAYER_NUM}
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

    public void start() {
        this.eventCards.shuffle();
        this.treasureCards.shuffle();
        this.distributeCards();
        this.currentPlayer = this.players.get(this.random.nextInt(this.getPlayerNum()));
        this.currentPlayer.levelUp(9);
    }


    private void distributeCards() {
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                player.addCard(treasureCards.draw());
                player.addCard(eventCards.draw());
            }
        }
    }

    /**
     * @return The player who is level 10 or null if no player have finished the game
     */
    public Player isGameFinsihed() {
        for (final Player player : players) {
            if (player.getLevel() >= MAX_LEVEL) {
                return player;
            }
        }
        return null;
    }

    private void createCards() {


        final List<String[]> cardData = CSVFileReader.readCSV("cards.csv");
        for (final String[] card : cardData) {
            final int cardID = Integer.parseInt(card[0]);
            final int cardCount = Integer.parseInt(card[1]); // Récupérer le nombre de fois que vous voulez la carte
            switch (cardID) {
                case MONSTER_CARD:
                    for (int i = 0; i < cardCount; i++) {
                        this.addCard(new MonsterCard(card[2], card[3], Integer.parseInt(card[4]), Integer.parseInt(card[5]), Integer.parseInt(card[6]), Integer.parseInt(card[7]), Integer.parseInt(card[8])));
                    }
                    break;
                case SINGLE_USE_CARD:
                    for (int i = 0; i < cardCount; i++) {
                        this.addCard(new SingleUseCard(card[2], card[3], Integer.parseInt(card[4]), 15, CardTargetMode.OTHER_PLAYER, EffectsDefinitions.LEVEL_DOWN));
                    }
                    break;
                case STUFF_CARD:
                    for (int i = 0; i < cardCount; i++) {
                        this.addCard(new StuffCard(card[2], card[3], Integer.parseInt(card[4]), Integer.parseInt(card[5]), card[6]));
                    }
                    break;
                case CLASS_CARD:
                    for (int i = 0; i < cardCount; i++) {
                        this.addCard(new ClassCard(card[2], card[3], card[2]));
                    }
                    break;
                case LANGUAGE_CARD:
                    for (int i = 0; i < cardCount; i++) {
                        this.addCard(new LanguageCard(card[2], card[3], card[2]));
                    }
                    break;
                case CURSE_CARD:
                    for (int i = 0; i < cardCount; i++) {
                        //TODO, les cartes malédiction peuvent cibler que nous ?
                        this.addCard(new CurseCard(card[2], card[3], Integer.parseInt(card[4]), CardTargetMode.SELF));
                    }
                    break;
                default:
                    System.err.println("[ERROR] Invalid ID : " + cardID + " while creating cards");
                    System.exit(1);
                    break;
            }
        }
    }

    private void addCard(Card card) {
        if(card instanceof EventCard) {
            this.eventCards.add((EventCard)card);
        }else if (card instanceof TreasureCard) {
            this.treasureCards.add((TreasureCard)card);
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
        final StringBuilder out = new StringBuilder("There are " + this.players.size() + " players\n");
        for (Player p : this.players) {
            out.append(p + "\n");
        }
        return out.toString();
    }

    private boolean isNameValid(final String name) {
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
        final EventCard cardDrawn = this.eventCards.draw();
        return cardDrawn;
    }

    public TreasureCard drawFromTreasureStack() throws NoSuchElementException {
        return this.treasureCards.draw();
    }

    /**
     * Returns true if he current player can finish his turn otherwise throws an error
     * @return
     * @throws TooManyCardsInHandException
     * @throws PlayerMustDrawException
     */
    public boolean canFinishTurn() throws TooManyCardsInHandException, PlayerMustDrawException {
        if (!this.currentPlayer.getHasDrawn()) {
            throw new PlayerMustDrawException();
        }
        if (this.currentPlayer.getHand().size() > Game.MAX_CARD_IN_HAND) {
            throw new TooManyCardsInHandException();
        }
        return true;
    }

    public Player playerlvmin(){
        int lvmin = MAX_LEVEL;
        assert players.size() != 0;
        Player tempPlayer = null;
        for (Player player : players){
            if (player.getLevel() < lvmin){
                tempPlayer = player;
                lvmin = player.getLevel();
            }
        }
        return tempPlayer;
    }

    public void discard(Card card) {
        this.currentPlayer.removeCardFromHand(card);
        Player minlvPlayer=this.playerlvmin();
        if (minlvPlayer==this.currentPlayer){
            this.discardPile.add(card);
        }
        else{
            this.playerlvmin().addCard(card);
        }

    }

    public void applyCurseEffect(CurseCard card) {
        switch (card.getTargetMode()) {
            case EVERYONE:
                card.applyEffect(this.players);
            default:
                card.applyEffect(this.currentPlayer);
                break;
        }
    }

    public Combat startCombat(Player player, MonsterCard monster, List<Card> effectCards) {
        final Combat combat = new Combat(this.currentPlayer, monster, this);
        System.out.println(combat.gMonsterCard().getStrength());
        for (final Card card : effectCards) {
            combat.changeMonsterStats(1);
        }
        System.out.println(combat.gMonsterCard().getStrength());
        return combat;
    }
    
    public int rollDice() {
        return (this.random.nextInt() % 6) + 1;
    }
}
