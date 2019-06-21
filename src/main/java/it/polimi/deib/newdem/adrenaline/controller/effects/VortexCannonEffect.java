package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.*;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class VortexCannonEffect implements Effect {

    private static final int BLACK_HOLE = 1;

    private static final PaymentInvoice BLACK_HOLE_PAYMENT = new PaymentInvoice(1,0,0,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        Tile vortexTile = manager.bindTile(new IntersectTileSelector(
                new VisibleTileSelector(actor.getTile()),
                new NearTileSelector(actor, 1, 100)
        ));

        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new NearPlayerSelector(vortexTile, 0,1));
        if (redPlayer.getTile() != vortexTile) {
            manager.movePlayer(redPlayer, vortexTile);
        }
        manager.damagePlayer(actor, redPlayer, 2, 0);

        if (manager.pay(BLACK_HOLE, BLACK_HOLE_PAYMENT)) {
            blackHole(manager, actor, vortexTile);
        }

    }

    private void blackHole(EffectManager manager, Player actor, Tile vortexTile) throws UndoException {

        Player bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, new NearPlayerSelector(vortexTile, 0, 1));

        if (bluePlayer.getTile() != vortexTile) {
            manager.movePlayer(bluePlayer, vortexTile);
        }
        manager.damagePlayer(actor, bluePlayer, 1, 0);


        Player greenPlayer = manager.bindPlayer(MetaPlayer.GREEN,
                new NearPlayerSelector(vortexTile, 0, 1), false);

        if (greenPlayer != null) {
            if (greenPlayer.getTile() != vortexTile) {
                manager.movePlayer(greenPlayer, vortexTile);
            }
            manager.damagePlayer(actor, greenPlayer, 1, 0);
        }
    }

}
