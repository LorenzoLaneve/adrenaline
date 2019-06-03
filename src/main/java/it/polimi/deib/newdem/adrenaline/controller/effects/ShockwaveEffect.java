package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.*;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class ShockwaveEffect implements Effect {

    private static final int TSUNAMI_MODE = 1;

    private static final PaymentInvoice TSUNAMI_MODE_PAYMENT = new PaymentInvoice(0,0,1,0);

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        if (!visitor.requestPayment(attacker, TSUNAMI_MODE_PAYMENT, TSUNAMI_MODE)) {

            Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new NearPlayerSelector(attacker, 1,1));
            visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 1, 0));

            PlayerSelector blueSelector = new IntersectPlayerSelector(
                    new NegatedPlayerSelector(new SameTilePlayerSelector(redPlayer)),
                    new NearPlayerSelector(attacker, 1,1)
            );

            Player bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE, blueSelector, false);
            if (bluePlayer != null) {
                visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 1, 0));

                PlayerSelector greenSelector = new IntersectPlayerSelector(
                    new NegatedPlayerSelector(new SameTilePlayerSelector(bluePlayer)),
                    blueSelector
                );

                Player greenPlayer = visitor.getBoundPlayer(MetaPlayer.GREEN, greenSelector, false);
                if (greenPlayer != null) {
                    visitor.reportGameChange(new DamageGameChange(attacker, greenPlayer, 1, 0));
                }
            }


        } else {

            for (Tile adjTile : attacker.getTile().getAdjacentTiles()) {
                for (Player player : adjTile.getPlayers()) {
                    visitor.reportGameChange(new DamageGameChange(attacker, player, 1,0));
                }
            }

        }

    }

}
