package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class EntryPointFactory {

    private EntryPointType type;

    private Integer minDist;
    private Integer maxDist;
    private Player attacker;

    public EntryPointFactory(EntryPointType type) {
        this.type = type;
    }

    public void setMinDist(int minDist) {
        this.minDist = minDist;
    }

    public void setMaxDist(int maxDist) {
        this.maxDist = maxDist;
    }

    public void setAttacker(Player attacker) {
        this.attacker = attacker; }

    public Interaction makeInteraction(InteractionContext context) {
        switch (type) {
            case GRAB:
                return new GetTileInteraction(context);
            case MOVEMENT:
                if(null == minDist || null == maxDist) throw new IllegalStateException();
                return new MovmentInteraction(context, minDist, maxDist);
            case SHOOT:
                return new SelectShootWeaponInteraction(context);
            case POWERUP:
                return new SelectPupInteraction(context);
            case RELOAD:
                return new SelectReloadWeaponInteraction(context);
            case REVENGE:
                return new SelectRevengePupInteraction(context);
            default:
                return null;
        }
    }

}
