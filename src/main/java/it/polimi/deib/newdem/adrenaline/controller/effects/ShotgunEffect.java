package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.utils.EffectSwitch;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class ShotgunEffect implements Effect {

    private static final int BASIC_MODE = 1;

    private static final int LONG_BARREL_MODE = 2;


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        EffectSwitch.create(BASIC_MODE, LONG_BARREL_MODE)
                .when(BASIC_MODE, this::basicMode)
                .when(LONG_BARREL_MODE, this::longBarrelMode)
                .executeOne(manager, actor);

    }

    private void basicMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new NearPlayerSelector(actor, 0, 0));

        manager.damagePlayer(actor, redPlayer, 3, 0);

        Tile destTile = manager.bindTile(new NearTileSelector(actor, 0, 1));

        manager.movePlayer(redPlayer, destTile);
    }

    private void longBarrelMode(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new NearPlayerSelector(actor, 1,1));

        manager.damagePlayer(actor, redPlayer, 2, 0);
    }

}
