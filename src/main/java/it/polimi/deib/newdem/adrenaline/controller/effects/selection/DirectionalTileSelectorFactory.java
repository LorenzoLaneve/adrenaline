package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

public class DirectionalTileSelectorFactory implements TileSelectorFactory {

    private int minDistance;
    private int maxDistance;
    private boolean ignoreWalls;
    private TilePosition sourceTilePosition;

    public DirectionalTileSelectorFactory(TilePosition sourceTilePosition,int minDist, int maxDist, boolean ignoreWalls){
        this.minDistance = minDist;
        this.maxDistance = maxDist;
        this.ignoreWalls = ignoreWalls;
        this.sourceTilePosition = sourceTilePosition;
    }

    @Override
    public TileSelector makeSelector(EffectVisitor visitor) {
        return new DirectionalTileSelector(sourceTilePosition,minDistance, maxDistance, ignoreWalls);
    }
}
