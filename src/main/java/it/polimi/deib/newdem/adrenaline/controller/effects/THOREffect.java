package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Card effect that implements Adrenaline' T.H.O.R. weapon card.
 * @see Effect for further information about the card effects.
 */
public class THOREffect implements Effect {

    private static final int CHAIN_REACTION = 1;

    private static final int HIGH_VOLTAGE = 2;

    private static final PaymentInvoice CHAIN_REACTION_PAYMENT = new PaymentInvoice(0,1,0,0);

    private static final PaymentInvoice HIGH_VOLTAGE_PAYMENT = new PaymentInvoice(0,1,0,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new VisiblePlayerSelector(actor));
        manager.damagePlayer(actor, redPlayer, 2, 0);

        if (manager.pay(CHAIN_REACTION, CHAIN_REACTION_PAYMENT)) {
            Player bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, new VisiblePlayerSelector(redPlayer));
            manager.damagePlayer(actor, bluePlayer, 1, 0);

            if (manager.pay(HIGH_VOLTAGE, HIGH_VOLTAGE_PAYMENT)) {
                Player greenPlayer = manager.bindPlayer(MetaPlayer.GREEN, new VisiblePlayerSelector(bluePlayer));
                manager.damagePlayer(actor, greenPlayer, 2, 0);
            }
        }
    }

}
