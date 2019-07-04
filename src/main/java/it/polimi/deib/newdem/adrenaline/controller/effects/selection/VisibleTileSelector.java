package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Room;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class VisibleTileSelector implements TileSelector{

    private Tile sourceTile;

    /**
     * Selects all the tiles that are visible from the given tile, according to Adrenaline rules.
     * @see TileSelector for further information
     */
    public VisibleTileSelector(Tile tile) {
        this.sourceTile = tile;
    }

    /**
     * Selects all the tiles that are visible by the given player, according to Adrenaline rules.
     * @see TileSelector for further information
     */
    public VisibleTileSelector(Player player) {
        this(player.getTile());
    }

    @Override
    public boolean isSelectable(Map map, Tile tile) {

        HashSet<Room> visibleRooms = new HashSet<Room>();

        visibleRooms.add(sourceTile.getRoom());

        for(Tile adTile: sourceTile.getAdjacentTiles()){
            visibleRooms.add(adTile.getRoom());
        }

        return visibleRooms.contains(tile.getRoom());
    }
}
