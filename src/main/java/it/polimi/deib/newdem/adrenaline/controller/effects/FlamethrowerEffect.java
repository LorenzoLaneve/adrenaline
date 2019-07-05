package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.DirectionalPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.IntersectPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Direction;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.Iterator;

/**
 * Card effect that implements Adrenaline' Flamethrower weapon card.
 * @see Effect for further information about the card effects.
 */
public class FlamethrowerEffect implements Effect {

    private static final int BARBECUE_MODE = 1;

    private static final PaymentInvoice BARBECUE_MODE_PAYMENT = new PaymentInvoice(0,0,2,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        if (!manager.pay(BARBECUE_MODE, BARBECUE_MODE_PAYMENT)) {
            basicMode(manager, actor);
        } else {
            barbecueMode(manager, actor);
        }

    }

    private void basicMode(EffectManager manager, Player actor) throws UndoException {

        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new NearPlayerSelector(actor, 1, 1), false);

        Player bluePlayer;
        if (redPlayer != null) {
            manager.damagePlayer(actor, redPlayer, 1, 0);

            Direction direction = actor.getTile().getDirection(redPlayer.getTile());

            bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, new IntersectPlayerSelector(
                    new DirectionalPlayerSelector(actor, direction, false),
                    new NearPlayerSelector(actor, 2, 2)
            ), false);

        } else {

            bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, new IntersectPlayerSelector(
                    new DirectionalPlayerSelector(actor, false),
                    new NearPlayerSelector(actor, 2, 2)
            ));
        }

        if (bluePlayer != null) {
            manager.damagePlayer(actor, bluePlayer, 1, 0);
        }

    }

    private void barbecueMode(EffectManager manager, Player actor) throws UndoException {

        Tile targetTile = manager.bindTile(new NearTileSelector(actor, 1, 1));

        Direction direction = actor.getTile().getDirection(targetTile);

        Iterator<Tile> tilesIterator = actor.getTile().getTiles(direction,false).iterator();
        int dmg = 2;
        if(tilesIterator.hasNext()) {
            tilesIterator.next();
        }
        while (dmg > 0 && tilesIterator.hasNext()) {
            Tile tile = tilesIterator.next();

            for (Player player : tile.getPlayers()) {
                manager.damagePlayer(actor, player, dmg, 0);
            }

            dmg--;
        }

    }

}
