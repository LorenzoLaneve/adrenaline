package it.polimi.deib.newdem.adrenaline.common.mechs.changes;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Game;
import it.polimi.deib.newdem.adrenaline.common.mechs.game.GameChange;
import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.map.Tile;

public class MovementGameChange implements GameChange {

    private Player player;

    private Tile sourceTile;
    private Tile destinationTile;

    public MovementGameChange(Player player, Tile srcTile, Tile destTile){
        //TODO
    }

    @Override
    public void update(Game game) {
        //TODO
    }

    @Override
    public void revert(Game game) {
        //TODO
    }
}
