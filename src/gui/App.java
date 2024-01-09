package src.gui;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import src.jeu.Combat;
import src.jeu.Game;
import src.jeu.Player;
import src.jeu.Cards.Card;
import src.jeu.Cards.CardTargetMode;
import src.jeu.Cards.ClassCard;
import src.jeu.Cards.CurseCard;
import src.jeu.Cards.LanguageCard;
import src.jeu.Cards.EventCard;
import src.jeu.Cards.MonsterCard;
import src.jeu.Cards.SingleUseCard;
import src.jeu.Cards.StuffCard;
import src.jeu.Exceptions.InvalidPlayerNameException;
import src.jeu.Exceptions.PlayerMustDrawException;
import src.jeu.Exceptions.SamePlayerException;
import src.jeu.Exceptions.TooManyCardsInHandException;
import src.jeu.Exceptions.TooManyPlayersException;

/**
 * @brief Classe principale de l'application
 * 
 * @param APP_TITLE Titre de l'application
 * @param APP_WIDTH Largeur de l'application
 * @param APP_HEIGHT Hauteur de l'application
 * @param game Instance de la classe Game
 * @param selectedCardButton Bouton de carte sélectionné par le joueur
 */
public final class App extends GameWindow {
    private static final String APP_TITLE = "Munchkin UTBM";
    private static final int APP_WIDTH    = 1600;
    private static final int APP_HEIGHT   = 900;
    
    private final Game game;
    private CardButton selectedCardButton;

    /**
     * @brief Constructeur de l'application
     */
    public App(){
        super(APP_TITLE, APP_WIDTH, APP_HEIGHT);
        this.game = new Game();
        this.selectedCardButton = null;
    }

    /**
     * @brief Zone de saisie du nom du joueur
     * @details Ajoute le joueur à la partie si le nom est valide
     */
    private void nameInputHandler(){
        final String text = super.mainMenu.textField.getText();
        super.mainMenu.textField.setText("");
        try{
            this.game.addPlayer(text);
            super.mainMenu.updateTextArea("Joueurs présents :\n" + game.getPlayerString());
            if(this.game.getPlayerNum() == Game.MIN_PLAYER_NUM){
                super.mainMenu.startGameButton.addActionListener(e -> this.startGame());
                super.mainMenu.startGameButton.setEnabled(true);
            }
        }
        catch(InvalidPlayerNameException invalidNameEx){
            super.announce("Le nom que vous avez entré est invalide!");
        }
        catch(SamePlayerException spex){
            super.announce("Ne peux pas ajouter : " + text + "\nCe nom est déjà utilisé!");
        }
        catch(TooManyPlayersException ex){
            super.mainMenu.addPlayerButton.removeActionListener(e -> this.nameInputHandler());
            super.mainMenu.addPlayerButton.setEnabled(false);
            super.announce("Ne peux pas ajouter : " + text + "\nIl y a déjà 6 joueurs!");
        }
    }

    /**
     * @brief Lance l'application
     * @details Ajoute les listeners aux boutons du menu principal
     */
    public void launch(){
        super.mainMenu.textField.addActionListener(e -> nameInputHandler());
        super.mainMenu.addPlayerButton.addActionListener(e -> nameInputHandler());
    }

    /**
     * @brief Lance la partie
     * @details Cache le menu principal et affiche le menu de jeu
     */
    public void startGame(){
        super.mainMenu.hide();
        this.game.start();
        super.playingMenu.show();
        this.update();
        super.playingMenu.getNextPlayerButton().addActionListener(e -> this.nextTurn());
        super.playingMenu.getPlayCardButton().addActionListener(e -> this.playSelectedCard());
        this.updateActionButton("Piocher", (e -> this.drawFromEventStack()));
    }

    /**
     * @brief Met à jour l'affichage
     * @details Met à jour le nom du joueur, les informations du joueur, les boutons de cartes
     */
    private void updateDisplay() {
        super.playingMenu.setPlayerNameLabelText("Tour de " + this.game.getCurrentPlayer().getName());
        super.playingMenu.clearCardButtons();
        super.playingMenu.updatePlayerInfoDisplay(this.game.getCurrentPlayer().getInfoString());
        final ArrayList<Card> currentPlayerHand = this.game.getCurrentPlayer().getHand();
        for(final Card card : currentPlayerHand) {
            final CardButton cardButton = new CardButton(card);
            this.applyBackgroundColor(cardButton);
            cardButton.addActionListener(l -> {
                this.selectCardButton(cardButton);
            });
            super.playingMenu.addCardButton(cardButton);
        }
        super.repaint();
    }

    /**
     * @brief Applique une couleur de fond au bouton de carte
     * @param cardButton Bouton de carte
     */
    private void applyBackgroundColor(CardButton cardButton) {
        Color backgroundColor = Color.green;
        final Card card = cardButton.getCard();
        if(card instanceof MonsterCard) {
            backgroundColor = CardButton.MONSTER_COLOR;
        }else if(card instanceof SingleUseCard) {
            backgroundColor = CardButton.SINGLE_USE_COLOR;
        }else if(card instanceof CurseCard) {
            backgroundColor = CardButton.CURSE_COLOR;
        }else if(card instanceof StuffCard) {
            backgroundColor = CardButton.STUFF_COLOR;
        }else if(card instanceof LanguageCard) {
            backgroundColor = CardButton.LANGUAGE_COLOR;
        }else if(card instanceof ClassCard) {
            backgroundColor = CardButton.CLASS_COLOR;
        }
        cardButton.setBackground(backgroundColor);
    }

    /**
     * @brief Met à jour l'affichage
     */
    private void update() {
        this.updateDisplay();
    }

    /**
     * @brief Crée une chaîne de caractères contenant les informations de la partie
     * @return Chaîne de caractères contenant les informations de la partie
     */
    private String getGameStateString() {
        final StringBuilder sb = new StringBuilder();
        for(final Player p : this.game.getPlayers()) {
            sb.append(p.getName()).append(" Level: ").append(p.getLevel()).append("\n");
        }
        return sb.toString();
    }

    /**
     * @brief Passe au tour suivant
     * @details Vérifie si la partie est terminée, sinon passe au tour suivant
     */
    private void nextTurn() {
        Player player;
        if((player = this.game.isGameFinsihed()) != null) {
            super.playingMenu.hide();
            this.revalidate();
            super.endMenu.show();
            super.endMenu.setText(player.getName(), this.getGameStateString());
            return;
        }
        try {
            this.game.nextTurn();
            this.updateActionButton("Piocher", (e -> this.drawFromEventStack()));
            this.playingMenu.clearCardButtons();
            super.playingMenu.getActionButton().setEnabled(true);
            this.update();
        } catch(TooManyCardsInHandException ex) {
            super.announce("Vous devez vous défausser pour continuer");
        } catch(PlayerMustDrawException ex) {
            super.announce("Vous devez piocher !");
        }
    }

    /**
     * Défausse la carte sélectionnée par le joueur
     */
    private void discardSelectedCard() {
        if(this.selectedCardButton == null) {
            return;
        }
        final Card card = this.selectedCardButton.getCard();
        this.game.getCurrentPlayer().removeCardFromHand(card);
        this.game.discard(card);
        this.unselectCardButton(selectedCardButton);
        this.updateDisplay();
    }

    /**
     * @brief Pioche une carte dans la pile d'événements
     * @details Si la carte est une carte malédiction, applique l'effet de la carte
     * Sinon, ajoute la carte à la main du joueur
     * Si la carte est un monstre, demande au joueur s'il veut combattre le monstre
     * Si le joueur accepte, lance le combat
     * Sinon, lance un dé pour savoir si le joueur s'enfuit
     * Si le joueur s'enfuit, affiche un message
     */
    private void drawFromEventStack() {
        if(this.game.getCurrentPlayer().getHasDrawn()) {
            super.announce("Vous avez déjà pioché");
            return;
        }
        try{
            final EventCard cardDrawn = this.game.drawFromEventStack();
            this.updateActionButton("Défausse", (e -> this.discardSelectedCard()));
            if(cardDrawn instanceof CurseCard) {
                this.game.applyCurseEffect((CurseCard) cardDrawn);
                super.announce("Vous avez pioché " + cardDrawn.getName() + "\n" + cardDrawn.getDescription());
            }
            else if(cardDrawn instanceof MonsterCard) {
                super.announce("Vous avez pioché " + cardDrawn.getName());
                MonsterCard monster = ((MonsterCard) cardDrawn);
                final int fightAnswer = JOptionPane.showConfirmDialog(null, "Voulez-vous combattre " + monster.getName() + "?\nNiveau : " + monster.getStrength(), "ça devient sérieux", JOptionPane.YES_NO_OPTION);
                if(fightAnswer == JOptionPane.YES_OPTION) {
                    this.fightCombat((MonsterCard) cardDrawn);
                    return;
                }
                else {
                    int diceResult = this.game.rollDice();
                    if(this.game.getCurrentPlayer().getDodge() < diceResult) {
                        super.announce("Vous avez perdu !");
                        return;
                    }
                }
                super.announce("Vous avez réussi à vous enfuir !");
                return;
            }
            else {
                super.announce("Vous avez pioché " + cardDrawn.getName());
                this.game.getCurrentPlayer().addCard(cardDrawn);
            }
            this.updateActionButton("Défausse", (e -> this.discardSelectedCard()));
            this.updateDisplay();

        }catch(NoSuchElementException ex) {
            System.err.println("[ERROR] Empty stack");
        }
    }

    /**
     * @brief Demande au joueur s'il veut utiliser une carte pour affecter le combat
     * @return Liste des cartes sélectionnées par le joueur
     */
    private ArrayList<SingleUseCard> askForEffectCards() {
        final ArrayList<SingleUseCard> result = new ArrayList<>();
        for(final Player player : this.game.getPlayers()) {
            final ArrayList<SingleUseCard> validCards = this.getValidBuffCards(player);
            if(validCards.size() != 0) {
                final int answer = JOptionPane.showOptionDialog(null, player.getName() + " Choisissez une carte pour affecter le combat", "Vous voulez augmenter la difficulté", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, validCards.toArray(), validCards.get(0));
                if(answer >= 0) {
                    final SingleUseCard chosenCard = validCards.get(answer);
                    if(player.equals(this.game.getCurrentPlayer())) {
                        chosenCard.applyEffect(player);
                    }else {
                        result.add(chosenCard);
                    }
                    player.getHand().remove(chosenCard);
                    
                }
            }
        }
        return result;
    }

    /**
     * @brief Retourne la liste des cartes qui peuvent être utilisées pour affecter le combat
     * @param player Joueur dont on veut récupérer les cartes
     * @return Liste des cartes qui peuvent être utilisées pour affecter le combat
     */
    private ArrayList<SingleUseCard> getValidBuffCards(Player player) {
        final ArrayList<SingleUseCard> validCards = new ArrayList<>();
        for(final Card card : player.getHand()) {
            if(card instanceof SingleUseCard && ((SingleUseCard)card).getTargetMode() == CardTargetMode.MONSTER_OR_PLAYER) {
                validCards.add((SingleUseCard) card);
            }
        }
        return validCards;
    }

    /**
     * @brief Joue la carte sélectionnée par le joueur
     */
    private void playSelectedCard(){
        if(this.selectedCardButton == null) {
            super.announce("Ne peux pas jouer la carte car aucune n'est sélectionnée!");
            return;
        }
        final Card selectedCard = this.selectedCardButton.getCard();
        this.game.getCurrentPlayer().removeCardFromHand(selectedCard);
        this.game.discard(selectedCard);
        this.unselectCardButton(selectedCardButton);
        this.update();
        if(selectedCard instanceof MonsterCard) {
            this.fightCombat((MonsterCard)selectedCard);
            return;
        }
        if(selectedCard.getTargetMode() == CardTargetMode.SELF) {
            selectedCard.applyEffect(this.game.getCurrentPlayer());
        }
        else if (selectedCard.getTargetMode() == CardTargetMode.ANYONE) {
            final Player target = this.askForTarget();
            if(target != null) {
                selectedCard.applyEffect(target);
            }
        }
        else if(selectedCard.getTargetMode() == CardTargetMode.EVERYONE) {
            assert selectedCard instanceof CurseCard;
            final CurseCard selectedCurseCard = ((CurseCard)selectedCard);
            selectedCurseCard.applyEffect(this.game.getPlayers());
        }
        this.update();
    }

    /**
     * @brief Fonction appelée quand le joueur clique sur une carte pour la surligner
     * @param cb Bouton de carte
     */
    private void selectCardButton(final CardButton cb) {
        if(this.selectedCardButton != null) {
            this.unselectCardButton(this.selectedCardButton);
        }
        cb.highlight();
        this.selectedCardButton = cb;
        this.selectedCardButton.clearListeners();
        this.selectedCardButton.addActionListener(e -> this.unselectCardButton(cb));
        super.revalidate();
    }

    /**
     * @brief Fonction appelée quand le joueur clique sur une carte pour la désélectionner
     * @param cb Bouton de carte
     */
    private void unselectCardButton(final CardButton cb) {
        if(cb == null) {
            System.err.println("[ERROR] Should not be possible");
        }
        cb.removeHighlight();
        this.selectedCardButton = null;
        cb.clearListeners();
        cb.addActionListener(e -> this.selectCardButton(cb));
        super.repaint();
    }

    /**
     * @brief Demande au joueur de choisir une cible
     * @return Joueur ciblé
     */
    private Player askForTarget() {
        final String[] playerNames =  this.playersToStringArray();
        int playerAnswer = JOptionPane.showOptionDialog(null, "Choisissez une cible", "Choisissez une cible", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, playerNames, playerNames[0]);
        try {
            return this.game.getPlayers().get(playerAnswer);
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * @brief Transforme la liste des joueurs en tableau de chaînes de caractères
     * @return Tableau de chaînes de caractères
     */
    private String[] playersToStringArray() {
        final String[] players = new String[this.game.getPlayers().size()];
        int i = 0;
        for(final Player p : this.game.getPlayers()) {
            String pName = p.getName();
            if(p.equals(this.game.getCurrentPlayer())){
                pName += " (vous)";
            }
            players[i] = pName;
            i++;
        }
        return players;
    }

    /**
     * @brief Lance un combat contre un monstre
     * @param monster Monstre
     */
    private void fightCombat(MonsterCard monster) {
        final ArrayList<SingleUseCard> effectCards = this.askForEffectCards();
        final Combat c = this.game.startCombat(this.game.getCurrentPlayer(), (MonsterCard) monster, effectCards);
        
        final boolean playerWon = c.fight();
        if(!playerWon) {
            super.announce("Vous avez perdu le combat !");
            return;
        }
        super.announce("Vous avez gagné le combat !");
        this.updateDisplay();
        return;
    }

    /**
     * @brief Met à jour le bouton d'action
     * @details Change le texte du bouton d'action et ajoute un listener
     * @param buttonText Texte du bouton
     * @param l Listener
     */
    private void updateActionButton(String buttonText, ActionListener l) {
        final MKButton actionButton = super.playingMenu.getActionButton();
        for(final ActionListener al : actionButton.getActionListeners()) {
            actionButton.removeActionListener(al);
        }
        actionButton.addActionListener(l);
        actionButton.setText(buttonText);
    }
}