package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;

public class UnionTileSelectorFactory implements TileSelectorFactory {

    private TileSelectorFactory firstOpFactory;
    private TileSelectorFactory secondOpFactory;

    public UnionTileSelectorFactory(TileSelectorFactory firstOpFactory, TileSelectorFactory secondOpFactory) {
        this.firstOpFactory = firstOpFactory;
        this.secondOpFactory = secondOpFactory;
    }

    @Override
    public TileSelector makeSelector(EffectVisitor visitor) {
        return new UnionTileSelector(firstOpFactory.makeSelector(visitor), secondOpFactory.makeSelector(visitor));
    }
}
