package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;

public class IntersectTileSelectorFactory implements TileSelectorFactory {

    private TileSelectorFactory firstOpFactory;
    private TileSelectorFactory secondOpFactory;

    public IntersectTileSelectorFactory(TileSelectorFactory firstOpFactory, TileSelectorFactory secondOpFactory) {
        this.firstOpFactory = firstOpFactory;
        this.secondOpFactory = secondOpFactory;
    }

    @Override
    public TileSelector makeSelector(EffectVisitor visitor) {
        return new IntersectTileSelector(firstOpFactory.makeSelector(visitor), secondOpFactory.makeSelector(visitor));
    }
}
