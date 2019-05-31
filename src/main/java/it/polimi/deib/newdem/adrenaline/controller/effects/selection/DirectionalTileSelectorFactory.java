package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class DirectionalTileSelectorFactory implements TileSelectorFactory {

    private int minDistance;
    private int maxDistance;
    private boolean ignoreWalls;
    private Tile sourceTile;

    public DirectionalTileSelectorFactory(Tile sourceTile,int minDist, int maxDist, boolean ignoreWalls){
        this.minDistance = minDist;
        this.maxDistance = maxDist;
        this.ignoreWalls = ignoreWalls;
        this.sourceTile = sourceTile;
    }

    @Override
    public TileSelector makeSelector(EffectVisitor visitor) {
        return new DirectionalTileSelector(sourceTile,minDistance, maxDistance, ignoreWalls);
    }
}
