package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.*;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class TractorBeamEffect implements Effect {

    private static final int PUNISHER_MODE = 1;

    private static final PaymentInvoice PUNISHER_MODE_PAYMENT = new PaymentInvoice(1,0,1,0);

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        if (!visitor.requestPayment(attacker, PUNISHER_MODE_PAYMENT, PUNISHER_MODE)) {
            Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new AnyPlayerSelector());

            Tile destinationTile = visitor.getTile(new IntersectTileSelector(
                    new VisibleTileSelector(attacker),
                    new NearTileSelector(redPlayer, 0, 2)
            ));

            visitor.reportGameChange(new MovementGameChange(redPlayer, destinationTile));
            visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 1, 0));
        } else {
            Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new NearPlayerSelector(attacker, 0, 2));

            visitor.reportGameChange(new MovementGameChange(redPlayer, attacker.getTile()));
            visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 3, 0));
        }

    }


}
