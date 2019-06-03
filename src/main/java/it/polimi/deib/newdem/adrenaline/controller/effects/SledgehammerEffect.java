package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.DirectionalTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.SameTilePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class SledgehammerEffect implements Effect {

    private static final int PULVERIZER_MODE = 1;

    private static final PaymentInvoice PULVERIZER_MODE_PAYMENT = new PaymentInvoice(1,0,0,0);

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        if (!visitor.requestPayment(attacker, PULVERIZER_MODE_PAYMENT, PULVERIZER_MODE)) {
            Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new SameTilePlayerSelector(attacker));

            visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2, 0));
        } else {
            Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new SameTilePlayerSelector(attacker));

            visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 3, 0));

            Tile destTile = visitor.getTile(new DirectionalTileSelector(attacker.getTile(), 0, 2, false));
            visitor.reportGameChange(new MovementGameChange(redPlayer, destTile));
        }

    }

}
