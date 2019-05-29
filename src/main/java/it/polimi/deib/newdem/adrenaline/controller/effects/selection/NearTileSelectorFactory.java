package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;

public class NearTileSelectorFactory implements TileSelectorFactory{

    private MetaPlayer sourcePlayer;
    private int minDistance;
    private int maxDistance;

    public NearTileSelectorFactory(MetaPlayer sourcePlayer, int minDist, int maxDist){
        this.sourcePlayer = sourcePlayer;
        this.minDistance = minDist;
        this.maxDistance = maxDist;
    }

    @Override
    public TileSelector makeSelector(EffectVisitor visitor) {
        return new NearTileSelector(visitor.getBoundPlayer(sourcePlayer).getTile(), minDistance, maxDistance);
    }
}
