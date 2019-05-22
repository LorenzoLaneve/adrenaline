package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class NearTileSelector implements TileSelector {

    private Tile sourceTile;
    private int minDistance;
    private int maxDistance;

    public NearTileSelector(Tile sourceTile, int minDist, int maxDist){
        this.sourceTile = sourceTile;
        this.minDistance = minDist;
        this.maxDistance = maxDist;
    }

    @Override
    public boolean isSelectable(Map map, Tile tile) {
        int distance = sourceTile.distanceFrom(tile);

        return minDistance < distance && distance < maxDistance;
    }
}
