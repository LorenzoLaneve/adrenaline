package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.AnyPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.DirectionalTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class NewtonEffect implements Effect {

    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new AnyPlayerSelector());

        TileSelector selector = new DirectionalTileSelector(redPlayer.getTile(), 1, 2, false);
        manager.movePlayer(redPlayer, manager.bindTile(selector));
    }

}
