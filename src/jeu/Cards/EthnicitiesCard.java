package src.jeu.Cards;

import src.jeu.GameClasses;
import src.jeu.Player;

public final class EthnicitiesCard extends EventCard{
    Ethnicities ethnie;
    
    public EthnicitiesCard(String name, String desc, String className, CardTargetMode targetMode) {
        super(name, desc, targetMode);
        this.ethnie = Ethnicities.getEthnicityFromName(className);
        this.setEffect((Player player) -> this.changePlayerEthnicities(player));
    }

    @Override
    public void applyEffect(Player target) {
        this.effect.effect(target);
    }

    private void changePlayerEthnicities(Player p){
        p.setEthnicity(this.ethnie);
    }
}
