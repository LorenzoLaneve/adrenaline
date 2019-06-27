package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class ReloadAction extends ActionBaseImpl {

    public ReloadAction(Player actor, ActionDataSource actionDataSource) {
        super(actor, actionDataSource, actor.getGame());
        // TODO implement
    }


    @Override
    public void damageDealtTrigger(Player attacker, Player victim) {

    }

    @Override
    public void damageTakenTrigger(Player attacker, Player victim) {

    }
}
