package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Card effect that implements Adrenaline' Electroscythe weapon card.
 * @see Effect for further information about the card effects.
 */
public class ElectroscytheEffect implements Effect {

    private static final int REAPER_MODE = 1;

    private static final PaymentInvoice REAPER_MODE_PAYMENT = new PaymentInvoice(1, 1, 0,0);

    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        int damage = 1;
        if (manager.pay(REAPER_MODE, REAPER_MODE_PAYMENT)) {
            damage = 2;
        }

        for (Player player : actor.getTile().getPlayers()) {
            if (player != actor) {
                manager.damagePlayer(actor, player, damage, 0);
            }
        }

    }
}
