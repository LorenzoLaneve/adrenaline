package it.polimi.deib.newdem.adrenaline.common.mechs.actions;

import it.polimi.deib.newdem.adrenaline.common.mechs.Player;

public class MoveAction extends ActionBaseImpl {

    private int minDist;
    private int maxDist;

    public MoveAction(Player actor,
                      int minDist,
                      int maxDist) {
        super(actor);
        this.minDist = minDist;
        this.maxDist = maxDist;
        // TODO implement
    }
}
