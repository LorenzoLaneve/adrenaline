package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Card effect that implements Adrenaline' Laser Magnum weapon card.
 * @see Effect for further information about the card effects.
 */
public class LaserMagnumEffect implements Effect {

    private static final int GIGAWATT_MODE = 1;

    private static final PaymentInvoice GIGAWATT_MODE_PAYMENT = new PaymentInvoice(1,1,1,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        if (!manager.pay(GIGAWATT_MODE, GIGAWATT_MODE_PAYMENT)) {
            basicMode(manager, actor);
        } else {
            gigawattMode(manager, actor);
        }

    }

    private void basicMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new VisiblePlayerSelector(actor));

        manager.damagePlayer(actor, redPlayer, 0,1);
    }

    private void gigawattMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new VisiblePlayerSelector(actor), false);

        if (redPlayer != null) {
            manager.damagePlayer(actor, redPlayer, 2, 0);

            PlayerSelector blueSelector = new VisiblePlayerSelector(actor);

            Player bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, blueSelector, false);
            if (bluePlayer != null) {
                manager.damagePlayer(actor, bluePlayer, 1, 0);
            }
        }
    }

}
