package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.utils.EffectSwitch;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Room;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class FurnaceEffect implements Effect {

    private static final int BASIC_MODE = 1;

    private static final int COZY_FIRE_MODE = 2;


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        EffectSwitch.create(BASIC_MODE, COZY_FIRE_MODE)
                .when(BASIC_MODE, this::basicMode)
                .when(COZY_FIRE_MODE, this::cozyFireMode)
                .executeOne(manager, actor);

    }

    private void basicMode(EffectManager manager, Player actor) throws UndoException {
        Tile targetRoomTile = manager.bindTile(
                new IntersectTileSelector(
                        new VisibleTileSelector(actor),
                        new NegatedTileSelector(new SameRoomTileSelector(actor))
                )
        );

        Room targetRoom = targetRoomTile.getRoom();

        for (Player player : targetRoom.getPlayers()) {
            manager.damagePlayer(actor, player, 1, 0);
        }
    }

    private void cozyFireMode(EffectManager manager, Player actor) throws UndoException {
        Tile targetTile = manager.bindTile(new NearTileSelector(actor, 1, 1));

        for (Player player : targetTile.getPlayers()) {
            manager.damagePlayer(actor, player, 1, 1);
        }
    }

}
