package it.polimi.deib.newdem.adrenaline.common.mechs.actions;

import it.polimi.deib.newdem.adrenaline.common.mechs.Player;

public class PickupAction extends ActionBaseImpl {

    private int minDist, maxDist;

    public PickupAction(Player actor, int minDist, int maxDist) {
        super(actor);
        this.minDist = minDist;
        this.maxDist = maxDist;
    }

}
