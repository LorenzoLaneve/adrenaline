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
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Tile vortexTile = visitor.getTile(new IntersectTileSelector(
                new VisibleTileSelector(attacker.getTile()),
                new NearTileSelector(attacker, 1, 100)
        ));

        List<Player> excludedPlayers = new ArrayList<>();

        Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new NearPlayerSelector(vortexTile, 0,1));
        if (redPlayer.getTile() != vortexTile) {
            visitor.reportGameChange(new MovementGameChange(redPlayer, vortexTile));
        }
        visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2, 0));

        if (visitor.requestPayment(attacker, BLACK_HOLE_PAYMENT, BLACK_HOLE)) {
            excludedPlayers.add(redPlayer);

            Player bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE,
                    new BlackListFilterPlayerSelector(excludedPlayers, new NearPlayerSelector(vortexTile, 0, 1)));

            if (bluePlayer.getTile() != vortexTile) {
                visitor.reportGameChange(new MovementGameChange(bluePlayer, vortexTile));
            }
            visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 1, 0));


            excludedPlayers.add(bluePlayer);
            Player greenPlayer = visitor.getBoundPlayer(MetaPlayer.GREEN,
                    new BlackListFilterPlayerSelector(excludedPlayers, new NearPlayerSelector(vortexTile, 0, 1)),
                                                        false);

            if (greenPlayer != null) {
                if (greenPlayer.getTile() != vortexTile) {
                    visitor.reportGameChange(new MovementGameChange(greenPlayer, vortexTile));
                }
                visitor.reportGameChange(new DamageGameChange(attacker, greenPlayer, 1, 0));
            }
        }

    }
}
