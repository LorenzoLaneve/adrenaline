package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.DirectionalPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.IntersectPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearPlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Direction;

/**
 * Card effect that implements Adrenaline' Power Glove weapon card.
 * @see Effect for further information about the card effects.
 */
public class PowerGloveEffect implements Effect {

    private static final int ROCKET_FIST = 1;

    private static final PaymentInvoice ROCKET_FIST_PAYMENT = new PaymentInvoice(0,1,0,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        if (!manager.pay(ROCKET_FIST, ROCKET_FIST_PAYMENT)) {
            basicMode(manager, actor);
        } else {
            rocketFistMode(manager, actor);
        }

    }

    private void basicMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new NearPlayerSelector(actor, 1, 1));

        manager.movePlayer(actor, redPlayer.getTile());
        manager.damagePlayer(actor, redPlayer, 1,2);
    }

    private void rocketFistMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new NearPlayerSelector(actor, 1, 1));

        Direction comboDirection = actor.getTile().getDirection(redPlayer.getTile());

        manager.movePlayer(actor, redPlayer.getTile());
        manager.damagePlayer(actor, redPlayer, 2, 0);

        Player bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, new IntersectPlayerSelector(
                new DirectionalPlayerSelector(actor, comboDirection, false),
                new NearPlayerSelector(actor, 1, 1)
        ), false);

        if (bluePlayer != null) {
            manager.movePlayer(actor, bluePlayer.getTile());
            manager.damagePlayer(actor, bluePlayer, 2, 0);
        }

    }

}
