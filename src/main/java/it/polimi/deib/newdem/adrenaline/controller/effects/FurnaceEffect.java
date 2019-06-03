package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.*;
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
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        List<Integer> choices = new ArrayList<>();
        choices.add(BASIC_MODE);
        choices.add(COZY_FIRE_MODE);

        switch (visitor.chooseEffect(choices)) {
            case BASIC_MODE:
                Tile targetRoomTile = visitor.getTile(
                        new IntersectTileSelector(
                                new VisibleTileSelector(attacker),
                                new NegatedTileSelector(new SameRoomTileSelector(attacker))
                        )
                );

                Room targetRoom = targetRoomTile.getRoom();

                for (Player player : targetRoom.getPlayers()) {
                    visitor.reportGameChange(new DamageGameChange(attacker, player, 1, 0));
                }

                break;
            case COZY_FIRE_MODE:
                Tile targetTile = visitor.getTile(new NearTileSelector(attacker, 1, 1));

                for (Player player : targetTile.getPlayers()) {
                    visitor.reportGameChange(new DamageGameChange(attacker, player, 1, 1));
                }

                break;
        }


    }

}
