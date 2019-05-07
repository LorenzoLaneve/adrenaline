package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.model.game.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

public class PowerUpCallAction extends ActionBaseImpl {

    private PowerUpCard card;
    /*
    At the time of writing, PowerUpCard hasn't been intrduced
    not even as a stub

    TODO validate
     */

    public PowerUpCallAction(Player actor, PowerUpCard card) {
        super(actor);
        this.card = card;
        // TODO implement

    }

    @Override
    public Effect getEffect() {
        //TODO implement
        return null;
    }
}
