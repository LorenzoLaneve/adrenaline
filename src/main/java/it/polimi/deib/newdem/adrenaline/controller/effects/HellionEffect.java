package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.IntersectTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisibleTileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class HellionEffect implements Effect {

    private static final int NANO_TRACER = 1;

    private static final PaymentInvoice NANO_TRACER_PAYMENT = new PaymentInvoice(1,0,0,0);


    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        int marks = 1;
        if (visitor.requestPayment(attacker, NANO_TRACER_PAYMENT, NANO_TRACER)) {
            marks = 2;
        }

        Tile targetTile = visitor.getTile(new IntersectTileSelector(
                new VisibleTileSelector(attacker),
                new NearTileSelector(attacker, 1, 100)
        ));

        for (Player player : targetTile.getPlayers()) {
            visitor.reportGameChange(new DamageGameChange(attacker, player, 1, marks));
        }

    }

}
