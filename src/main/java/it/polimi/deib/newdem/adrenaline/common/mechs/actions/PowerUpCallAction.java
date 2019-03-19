package it.polimi.deib.newdem.adrenaline.common.mechs.actions;

import it.polimi.deib.newdem.adrenaline.common.mechs.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.PowerUpCard;

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
    }

    public PowerUpCard getCard() {
        return card;
    }
}
