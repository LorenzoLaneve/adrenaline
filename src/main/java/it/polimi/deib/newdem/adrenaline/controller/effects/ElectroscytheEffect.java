package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class ElectroscytheEffect implements Effect {

    private static final int REAPER_MODE = 1;

    private static final PaymentInvoice REAPER_MODE_PAYMENT = new PaymentInvoice(1, 1, 0,0);

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        int damage = 1;

        if (visitor.requestPayment(attacker, REAPER_MODE_PAYMENT, REAPER_MODE)) {
            damage = 2;
        }

        for (Player player : attacker.getTile().getPlayers()) if (player != attacker) {
            visitor.reportGameChange(new DamageGameChange(attacker, player, damage, 0));
        }

    }

}
