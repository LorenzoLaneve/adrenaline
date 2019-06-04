package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.SameTilePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class ChainsawEffect implements Effect {

    private static final int DOOM_MODE = 1;

    private static final PaymentInvoice DOOM_MODE_PAYMENT = new PaymentInvoice(1,1,1,0);

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        int dmg = 1;
        if (visitor.requestPayment(attacker, DOOM_MODE_PAYMENT, DOOM_MODE)) {
            dmg = 3;
        }

        Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new SameTilePlayerSelector(attacker));
        visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 3,0));
    }

}
