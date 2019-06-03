package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class THOREffect implements Effect {

    private static final int CHAIN_REACTION = 1;

    private static final int HIGH_VOLTAGE = 2;

    private static final PaymentInvoice CHAIN_REACTION_PAYMENT = new PaymentInvoice(0,1,0,0);

    private static final PaymentInvoice HIGH_VOLTAGE_PAYMENT = new PaymentInvoice(0,1,0,0);


    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new VisiblePlayerSelector(attacker));
        visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2, 0));

        if (visitor.requestPayment(attacker, CHAIN_REACTION_PAYMENT, CHAIN_REACTION)) {

            Player bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE, new VisiblePlayerSelector(redPlayer));
            visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 1, 0));

            if (visitor.requestPayment(attacker, HIGH_VOLTAGE_PAYMENT, HIGH_VOLTAGE)) {
                Player greenPlayer = visitor.getBoundPlayer(MetaPlayer.GREEN, new VisiblePlayerSelector(bluePlayer));
                visitor.reportGameChange(new DamageGameChange(attacker, greenPlayer, 2, 0));
            }
        }
    }

}
