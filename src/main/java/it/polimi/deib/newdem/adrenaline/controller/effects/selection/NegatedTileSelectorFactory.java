package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;

public class NegatedTileSelectorFactory implements TileSelectorFactory {

    private TileSelectorFactory innerSelectorFactory;

    public NegatedTileSelectorFactory(TileSelectorFactory innerSelectorFactory) {
        this.innerSelectorFactory = innerSelectorFactory;
    }

    @Override
    public TileSelector makeSelector(EffectVisitor visitor) {
        return new NegatedTileSelector(innerSelectorFactory.makeSelector(visitor));
    }
}
