package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.AnyDirectionalTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.SameTilePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class SledgehammerEffect implements Effect {

    private static final int PULVERIZER_MODE = 1;

    private static final PaymentInvoice PULVERIZER_MODE_PAYMENT = new PaymentInvoice(1,0,0,0);

    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        if (!manager.pay(PULVERIZER_MODE, PULVERIZER_MODE_PAYMENT)) {
            basicMode(manager, actor);
        } else {
            pulverizerMode(manager, actor);
        }
    }

    private void basicMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new SameTilePlayerSelector(actor));

        manager.damagePlayer(actor, redPlayer, 2, 0);
    }

    private void pulverizerMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new SameTilePlayerSelector(actor));

        manager.damagePlayer(actor, redPlayer, 3, 0);

        Tile destTile = manager.bindTile(new AnyDirectionalTileSelector(actor.getTile(), 0, 2, false));
        manager.movePlayer(redPlayer, destTile);
    }

}
