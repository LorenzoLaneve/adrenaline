package it.polimi.deib.newdem.adrenaline.common.controller.effects;

import it.polimi.deib.newdem.adrenaline.common.model.map.Map;
import it.polimi.deib.newdem.adrenaline.common.model.map.Tile;

public class NearTileSelector implements TileSelector {

    private int minDistance;
    private int maxDistance;

    public NearTileSelector(int minDist, int maxDist){
        //TODO
    }

    @Override
    public boolean isSelectable(Map map, Tile tile) {
        //TODO
        return false;
    }
}
