package it.polimi.deib.newdem.adrenaline.common.controller.actions;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

public class PickupAction extends ActionBaseImpl {

    private int minDist;
    private int maxDist;

    public PickupAction(Player actor, int minDist, int maxDist) {
        super(actor);
        this.minDist = minDist;
        this.maxDist = maxDist;
        // TODO implement
    }

    @Override
    public Effect getEffect() {
        //TODO implement
        return null;
    }
}
