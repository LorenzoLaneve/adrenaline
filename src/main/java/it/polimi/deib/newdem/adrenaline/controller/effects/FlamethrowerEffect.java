package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.DirectionalPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.IntersectPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Direction;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.Iterator;

public class FlamethrowerEffect implements Effect {

    private static final int BARBECUE_MODE = 1;

    private static final PaymentInvoice BARBECUE_MODE_PAYMENT = new PaymentInvoice(0,0,2,0);

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        if (!visitor.requestPayment(attacker, BARBECUE_MODE_PAYMENT, BARBECUE_MODE)) {
            Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new NearPlayerSelector(attacker, 1, 1), false);

            Player bluePlayer;
            if (redPlayer != null) {
                visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 1, 0));

                Direction direction = attacker.getTile().getDirection(redPlayer.getTile());

                bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE, new IntersectPlayerSelector(
                        new DirectionalPlayerSelector(attacker, direction, false),
                        new NearPlayerSelector(attacker, 2,2)
                ), false);

            } else {

                bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE, new IntersectPlayerSelector(
                        new DirectionalPlayerSelector(attacker, false),
                        new NearPlayerSelector(attacker, 2,2)
                ));
            }

            if (bluePlayer != null) {
                visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 1, 0));
            }

        } else {
            Tile targetTile = visitor.getTile(new NearTileSelector(attacker, 1, 1));

            Direction direction = attacker.getTile().getDirection(targetTile);

            Iterator<Tile> tilesIterator = attacker.getTile().getTiles(direction,false).iterator();
            int dmg = 2;
            if(tilesIterator.hasNext()){
                Tile throwAwayTile = tilesIterator.next();
                String usesele = "pleaze";
            }
            while (dmg > 0 && tilesIterator.hasNext()) {
                Tile tile = tilesIterator.next();

                for (Player player : tile.getPlayers()) {
                    visitor.reportGameChange(new DamageGameChange(attacker, player, dmg, 0));
                }

                dmg--;
            }

        }


    }

}
