package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class DirectionalTileSelector implements TileSelector {

    int minDistance;
    int maxDistance;
    boolean ignoreWalls;

    public DirectionalTileSelector(int minDist, int maxDist, boolean ignoreWalls){
        //TODO
    }

    @Override
    public boolean isSelectable(Map map, Tile tile) {
        //TODO
        return false;
    }
}