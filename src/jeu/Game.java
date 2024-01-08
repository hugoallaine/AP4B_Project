package src.jeu;

import java.util.*;

import src.jeu.Cards.*;
import src.jeu.Exceptions.*;

/**
 * @brief Classe représentant le jeu
 * @details Cette classe contient toutes les informations relatives au jeu, comme les joueurs, les cartes, etc.
 * 
 * @param players Liste des joueurs
 * @param treasureCards Pile de cartes trésor
 * @param eventCards Pile de cartes évènement
 * @param discardPile Pile de cartes défaussées
 * @param currentPlayer Joueur actuel
 * @param random Générateur de nombres aléatoires
 */
public final class Game {
    private static final int MAX_PLAYER_NUM = 6;
    public static final int MIN_PLAYER_NUM = 3;
    public static final int MAX_CARD_IN_HAND = 5;

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

    /**
     * @brief Constructeur de la classe Game
     */
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

    /**
     * @brief Getter des noms des joueurs
     * @return les noms des joueurs
     */
    public String getPlayerString() {
        StringBuffer sb = new StringBuffer();
        for (Player player : this.players) {
            sb.append("- " + player.getName() + "\n");
        }
        return sb.toString();
    }

    /**
     * @brief Fonction qui vérifie si un joueur existe déjà
     * @param player_name Nom du joueur
     * @return true si le joueur existe déjà, false sinon
     */
    private boolean playerAlreadyExists(final String player_name) {
        for (final Player p : players) {
            if (p.getName().equals(player_name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @brief Getter du nombre de joueurs
     * @return le nombre de joueurs
     */
    public int getPlayerNum() {
        return this.players.size();
    }

    /**
     * @brief Ajoute un joueur à la liste des joueurs
     * @param playerName Nom du joueur
     * @throws TooManyPlayersException Si le nombre de joueurs est supérieur à 6
     * @throws SamePlayerException Si le joueur existe déjà
     * @throws InvalidPlayerNameException Si le nom du joueur est invalide
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

    /**
     * @brief Démarre le jeu
     * @details Mélange les cartes et distribue 2 cartes trésor et 2 cartes évènement à chaque joueur puis choisi un joueur au hasard pour commencer
     */
    public void start() {
        this.eventCards.shuffle();
        this.treasureCards.shuffle();
        this.distributeCards();
        this.currentPlayer = this.players.get(this.random.nextInt(this.getPlayerNum()));
        this.currentPlayer.levelUp(0);
    }

    /**
     * @brief Distribue 2 cartes trésor et 2 cartes évènement à chaque joueur
     */
    private void distributeCards() {
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                player.addCard(treasureCards.draw());
                player.addCard(eventCards.draw());
            }
        }
    }

    /**
     * @brief Vérifie si la partie est terminée
     * @return le joueur gagnant si la partie est terminée, null sinon
     */
    public Player isGameFinsihed() {
        for (final Player player : players) {
            if (player.getLevel() >= Player.MAX_LEVEL) {
                return player;
            }
        }
        return null;
    }

    /**
     * @brief Crée les cartes à partir du fichier cards.csv
     */
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
                        this.addCard(new SingleUseCard(card[2], card[3], Integer.parseInt(card[4]), Integer.parseInt(card[5]), card[6]));
                    }
                    break;
                case STUFF_CARD:
                    for (int i = 0; i < cardCount; i++) {
                        this.addCard(new StuffCard(card[2], card[3], Integer.parseInt(card[4]), card[5]));
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
                        this.addCard(new CurseCard(card[2], card[3], Integer.parseInt(card[4]), card[5]));
                    }
                    break;
                default:
                    System.err.println("[ERROR] Invalid ID : " + cardID + " while creating cards");
                    System.exit(1);
                    break;
            }
        }
    }

    /**
     * @brief Ajoute une carte à la pile de cartes
     * @details Si la carte est une carte évènement, on l'ajoute à la pile de cartes évènement, sinon on l'ajoute à la pile de cartes trésor
     * @param card Carte à ajouter
     */
    private void addCard(Card card) {
        if(card instanceof EventCard) {
            this.eventCards.add((EventCard)card);
        }else if (card instanceof TreasureCard) {
            this.treasureCards.add((TreasureCard)card);
        }
    }

    /**
     * @brief Passe au tour suivant
     * @details Passe au tour suivant si le joueur actuel a pioché une carte et n'a pas trop de cartes dans sa main
     * @throws TooManyCardsInHandException
     * @throws PlayerMustDrawException
     */
    public void nextTurn() throws TooManyCardsInHandException, PlayerMustDrawException {
        if (this.canFinishTurn()) {
            int currentPlayerIndex = this.players.indexOf(this.currentPlayer);
            this.currentPlayer.setHasDrawn(false);
            this.currentPlayer.resetBuffs();
            this.currentPlayer = this.players.get((currentPlayerIndex + 1) % this.players.size());
        }
    }

    /**
     * @brief Créer une chaîne de caractères contenant les informations de la partie (nombre de joueurs, joueurs)
     * @return la chaîne de caractères contenant les informations de la partie
     */
    @Override
    public String toString() {
        final StringBuilder out = new StringBuilder("There are " + this.players.size() + " players\n");
        for (Player p : this.players) {
            out.append(p + "\n");
        }
        return out.toString();
    }

    /**
     * @brief Vérifie si le nom du joueur est valide
     * @param name Nom du joueur
     * @return true si le nom du joueur est valide, false sinon
     */
    private boolean isNameValid(final String name) {
        return name != null && name.matches("^[a-zA-Z0-9]+$");
    }

    /**
     * @brief Getter du joueur actuel
     * @return le joueur actuel
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * @brief Getter de la liste des joueurs de la partie
     * @return les joueurs
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * @brief Pioche une carte dans la pile de cartes évènement
     * @return la carte piochée
     */
    public EventCard drawFromEventStack() {
        if(this.eventCards.isEmpty()) {
            this.reshuffle();
        }
        this.currentPlayer.setHasDrawn(true);
        final EventCard cardDrawn = this.eventCards.draw();
        return cardDrawn;
    }

    private void reshuffle() {
        for (final Object obj : this.discardPile) {
            Card card = (Card) obj;
            this.addCard(card);
        }
        this.eventCards.shuffle();
    }

    /**
     * @brief Pioche une carte dans la pile de cartes trésor
     * @return la carte piochée
     */
    public TreasureCard drawFromTreasureStack() {
        if(this.treasureCards.isEmpty()) {
            this.reshuffle();
        }
        return this.treasureCards.draw();
    }

    /**
     * @brief Vérifie si le joueur actuel peut finir son tour
     * @return true si le joueur actuel peut finir son tour, false sinon
     * @throws TooManyCardsInHandException Si le joueur a trop de cartes dans sa main
     * @throws PlayerMustDrawException Si le joueur n'a pas pioché de carte
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

    /**
     * @brief Vérifie si le joueur actuel peut piocher une carte
     * @return true si le joueur actuel peut piocher une carte, false sinon
     * @throws TooManyCardsInHandException Si le joueur a trop de cartes dans sa main
     * @throws PlayerMustDrawException Si le joueur a déjà pioché une carte
     */
    public Player playerlvmin(){
        int lvmin = Player.MAX_LEVEL;
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

    /**
     * @brief Envoie une carte dans la défausse
     * @details Si le joueur actuel a le même niveau que le joueur ayant le plus petit niveau, on ajoute la carte dans la pile de cartes défaussées, sinon on l'ajoute dans la main du joueur ayant le plus petit niveau
     * @param card Carte à envoyer dans la défausse
     */
    public void discard(Card card) {
        this.currentPlayer.removeCardFromHand(card);
        Player minlvPlayer=this.playerlvmin();
        if (minlvPlayer.getLevel()==this.currentPlayer.getLevel()){
            this.discardPile.add(card);
        }
        else{
            this.playerlvmin().addCard(card);
        }
    }

    /**
     * @brief Applique l'effet d'une carte malédiction
     * @details Si la carte malédiction cible tout le monde, on applique l'effet à tous les joueurs, sinon on applique l'effet au joueur actuel
     * @param card Carte malédiction
     */
    public void applyCurseEffect(CurseCard card) {
        switch (card.getTargetMode()) {
            case EVERYONE:
                card.applyEffect(this.players);
            default:
                card.applyEffect(this.currentPlayer);
                break;
        }
    }

    /**
     * @brief Démarre un combat
     * @details Crée un combat avec le joueur actuel, le monstre et les cartes à effet
     * @param player Joueur actuel
     * @param monster Monstre
     * @param effectCards Cartes à effet
     * @return le combat créé
     */
    public Combat startCombat(Player player, MonsterCard monster, List<SingleUseCard> effectCards) {
        final Combat combat = new Combat(this.currentPlayer, monster, this);
        for (final SingleUseCard card : effectCards) {
            combat.changeMonsterStats(card.getBuff());
        }
        return combat;
    }
    
    /**
     * @brief Lance un dé
     * @return le résultat du dé
     */
    public int rollDice() {
        return (this.random.nextInt() % 6) + 1;
    }
}