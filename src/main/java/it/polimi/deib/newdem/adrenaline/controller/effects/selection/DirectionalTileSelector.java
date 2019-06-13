package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.map.Direction;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class DirectionalTileSelector implements TileSelector {

    private int minDistance;
    private int maxDistance;
    private boolean ignoreWalls;
    private Tile sourceTile;


    public DirectionalTileSelector(Tile sourceTile, int minDist, int maxDist, boolean ignoreWalls) {
        this.sourceTile = sourceTile;
        this.minDistance = minDist;
        this.maxDistance = maxDist;
        this.ignoreWalls = ignoreWalls;
    }

    @Override
    public boolean isSelectable(Map map, Tile tile) {
        List<Tile> selectableTiles = new ArrayList<>();

        int distance = tile.distanceFrom(sourceTile);

        boolean distCond = (distance <= maxDistance && distance >= minDistance);

        selectableTiles.addAll(sourceTile.getTiles(Direction.WEST, ignoreWalls));
        selectableTiles.addAll(sourceTile.getTiles(Direction.EAST, ignoreWalls));
        selectableTiles.addAll(sourceTile.getTiles(Direction.NORTH, ignoreWalls));
        selectableTiles.addAll(sourceTile.getTiles(Direction.SOUTH, ignoreWalls));
        return selectableTiles.contains(tile) && distCond;
    }
}
