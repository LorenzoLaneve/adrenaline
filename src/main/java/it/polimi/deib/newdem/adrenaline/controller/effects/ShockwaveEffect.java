package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

/**
 * Card effect that implements Adrenaline' Shockwave weapon card.
 * @see Effect for further information about the card effects.
 */
public class ShockwaveEffect implements Effect {

    private static final int TSUNAMI_MODE = 1;

    private static final PaymentInvoice TSUNAMI_MODE_PAYMENT = new PaymentInvoice(0,0,1,0);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        if (!manager.pay(TSUNAMI_MODE, TSUNAMI_MODE_PAYMENT)) {
            basicMode(manager, actor);
        } else {
            tsunamiMode(manager, actor);
        }

    }

    private void basicMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new NearPlayerSelector(actor, 1, 1));
        manager.damagePlayer(actor, redPlayer, 1, 0);

        PlayerSelector blueSelector = new IntersectPlayerSelector(
                new NegatedPlayerSelector(new SameTilePlayerSelector(redPlayer)),
                new NearPlayerSelector(actor, 1, 1)
        );

        Player bluePlayer = manager.bindPlayer(MetaPlayer.BLUE, blueSelector, false);
        if (bluePlayer != null) {
            manager.damagePlayer(actor, bluePlayer, 1, 0);

            PlayerSelector greenSelector = new IntersectPlayerSelector(
                    new NegatedPlayerSelector(new SameTilePlayerSelector(bluePlayer)),
                    blueSelector
            );

            Player greenPlayer = manager.bindPlayer(MetaPlayer.GREEN, greenSelector, false);
            if (greenPlayer != null) {
                manager.damagePlayer(actor, greenPlayer, 1, 0);
            }
        }
    }

    private void tsunamiMode(EffectManager manager, Player actor) {

        for (Tile adjTile : actor.getTile().getAdjacentTiles()) {
            for (Player player : adjTile.getPlayers()) {
                manager.damagePlayer(actor, player, 1,0);
            }
        }

    }

}
