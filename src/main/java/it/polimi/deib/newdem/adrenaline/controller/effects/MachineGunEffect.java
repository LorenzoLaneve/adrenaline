package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;


public class MachineGunEffect implements Effect {

    private static final int FOCUS_SHOT = 1;

    private static final int TURRET_TRIPOD = 2;

    private static final PaymentInvoice FOCUS_SHOT_PAYMENT = new PaymentInvoice(0,0,1,0);

    private static final PaymentInvoice TURRET_TRIPOD_PAYMENT = new PaymentInvoice(0,1,0,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new VisiblePlayerSelector(actor));
        manager.damagePlayer(actor, redPlayer, 1, 0);

        Player bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, new VisiblePlayerSelector(actor), false);
        if (bluePlayer != null) {
            manager.damagePlayer(actor, bluePlayer, 1, 0);
        }

        if (manager.pay(FOCUS_SHOT, FOCUS_SHOT_PAYMENT)) {
            manager.damagePlayer(actor, redPlayer, 1, 0);
        }

        if (manager.pay(TURRET_TRIPOD, TURRET_TRIPOD_PAYMENT)) {
            if (bluePlayer != null) {
                manager.damagePlayer(actor, bluePlayer, 1,0);
            }

            Player greenPlayer = manager.bindPlayer(MetaPlayer.GREEN, new VisiblePlayerSelector(actor), false);
            if (greenPlayer != null) {
                manager.damagePlayer(actor, greenPlayer, 1, 0);
            }
        }

    }
}
