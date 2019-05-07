package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.model.game.Player;

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

    @Override
    public Effect getEffect() {
        //TODO implement
        return null;
    }
}
