package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

public class PowerUpCallAction extends ActionBasePlain {

    private PowerUpCard card;
    /*
    At the time of writing, PowerUpCard hasn't been intrduced
    not even as a stub

    TODO validate
     */

    public PowerUpCallAction(Player actor, ActionDataSource actionDataSource, PowerUpCard card) {
        super(actor, actionDataSource, actor.getGame());
        this.card = card;
        // TODO implement

    }
}
