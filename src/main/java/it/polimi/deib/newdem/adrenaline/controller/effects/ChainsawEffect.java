package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.SameTilePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Card effect that implements Adrenaline' Chainsaw weapon card.
 * @see Effect for further information about the card effects.
 */
public class ChainsawEffect implements Effect {

    private static final int DOOM_MODE = 1;

    private static final PaymentInvoice DOOM_MODE_PAYMENT = new PaymentInvoice(1,1,1,0);

    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        int dmg = 1;
        if (manager.pay(DOOM_MODE, DOOM_MODE_PAYMENT)) {
            dmg = 3;
        }

        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new SameTilePlayerSelector(actor));
        manager.damagePlayer(actor, redPlayer, dmg,0);
    }

}
