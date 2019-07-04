package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.IntersectTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisibleTileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

/**
 * Card effect that implements Adrenaline' Hellion weapon card.
 * @see Effect for further information about the card effects.
 */
public class HellionEffect implements Effect {

    private static final int NANO_TRACER = 1;

    private static final PaymentInvoice NANO_TRACER_PAYMENT = new PaymentInvoice(1,0,0,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        int marks = 1;
        if (manager.pay(NANO_TRACER, NANO_TRACER_PAYMENT)) {
            marks = 2;
        }

        Tile targetTile = manager.bindTile(new IntersectTileSelector(
                new VisibleTileSelector(actor),
                new NearTileSelector(actor, 1, 100)
        ));

        for (Player player : targetTile.getPlayers()) {
            manager.damagePlayer(actor, player, 1, marks);
        }
    }
}
