package src.jeu.Cards;

import src.jeu.Player;

public final class LanguageCard extends EventCard{
    Languages lang;
    
    public LanguageCard(String name, String desc, String language) {
        super(name, desc, CardTargetMode.SELF);
        this.lang = Languages.getLanguageFromName(language);
        this.setEffect((Player player) -> this.changePlayerLanguage(player));
    }

    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }

    private void changePlayerLanguage(Player p){
        p.setLanguage(this.lang);
    }
}
