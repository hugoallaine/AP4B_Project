package src.jeu.Cards;

import src.jeu.Player;

/**
 * @brief Classe des cartes de changement de langue (enfant de EventCard)
 * @details Les cartes de changement de langue sont des cartes évènement qui changent la langue d'un joueur
 * 
 * @param lang La langue de la carte
 */
public final class LanguageCard extends EventCard{
    Languages lang;
    
    /**
     * @brief Constructeur de la classe LanguageCard
     * @param name Le nom de la carte
     * @param desc La description de la carte
     * @param language La langue de la carte
     */
    public LanguageCard(String name, String desc, String language) {
        super(name, desc, CardTargetMode.SELF);
        this.lang = Languages.getLanguageFromName(language);
        this.setEffect((Player player) -> this.changePlayerLanguage(player));
    }

    /**
     * @brief Applique l'effet de la carte sur le joueur ciblé
     * @param target Le joueur ciblé
     */
    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }

    /**
     * @brief Change la langue du joueur
     * @param p Le joueur ciblé
     */
    private void changePlayerLanguage(Player p){
        p.setLanguage(this.lang);
    }
}