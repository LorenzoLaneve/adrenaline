package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Room;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class VisibleTileSelector implements TileSelector{

    private Tile sourceTile;

    public VisibleTileSelector(Tile tile) {
        this.sourceTile = tile;
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
