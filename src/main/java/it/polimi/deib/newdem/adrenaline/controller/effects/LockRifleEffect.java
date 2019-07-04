package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Card effect that implements Adrenaline' Lock Rifle weapon card.
 * @see Effect for further information about the card effects.
 */
public class LockRifleEffect implements Effect {

    private static final int SECOND_LOCK = 1;

    private static final PaymentInvoice SECOND_LOCK_PAYMENT = new PaymentInvoice(1,0,0,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new VisiblePlayerSelector(actor));

        manager.damagePlayer(actor, redPlayer, 2, 1);

        if (manager.pay(SECOND_LOCK, SECOND_LOCK_PAYMENT)) {
            Player bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, new VisiblePlayerSelector(actor));

            manager.damagePlayer(actor, bluePlayer, 0, 1);
        }
    }

}
