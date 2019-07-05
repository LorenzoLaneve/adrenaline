package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class EntryPointFactory {

    private EntryPointType type;

    private Integer minDist;
    private Integer maxDist;
    private Player attacker;

    /**
     * Builds a new {@code EntryPointFactory} for the given {@code EntryPointType}
     * @param type of action to build the entry point of
     */
    public EntryPointFactory(EntryPointType type) {
        this.type = type;
    }

    /**
     * Set this factory's minDist. This is significant for MOVE atoms
     * @param minDist minimum movement distance
     */
    public void setMinDist(int minDist) {
        this.minDist = minDist;
    }

    /**
     * Set this fatory's maximum distance. This is significant for MOVE atoms
     * @param maxDist maximum distance
     */
    public void setMaxDist(int maxDist) {
        this.maxDist = maxDist;
    }

    /**
     * Set this factory's attacker. This is significant for REVENGE atoms
     * @param attacker
     */
    public void setAttacker(Player attacker) {
        this.attacker = attacker; }

    /**
     * Builds a new {@code Interaction} from the data contained within this object and
     * the given {@code InteractionContext}
     * @param context to bind the new interaction to
     * @return a new {@code Interaction}
     * @throws IllegalStateException if it's attempted to create a factory without all the necessary information set
     */
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
