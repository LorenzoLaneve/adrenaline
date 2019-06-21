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
    public void apply(EffectManager manager, Player actor) throws UndoException {

        if (!manager.pay(PUNISHER_MODE, PUNISHER_MODE_PAYMENT)) {
            basicMode(manager, actor);
        } else {
            punisherMode(manager, actor);
        }

    }

    private void basicMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new AnyPlayerSelector());

        Tile destinationTile = manager.bindTile(new IntersectTileSelector(
                new VisibleTileSelector(actor),
                new NearTileSelector(redPlayer, 0, 2)
        ));

        manager.movePlayer(redPlayer, destinationTile);
        manager.damagePlayer(actor, redPlayer, 1, 0);
    }

    private void punisherMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new NearPlayerSelector(actor, 0, 2));

        manager.movePlayer(redPlayer, actor.getTile());
        manager.damagePlayer(actor, redPlayer, 3, 0);
    }

}
