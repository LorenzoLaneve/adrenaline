package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;

public class AnyTileSelectorFactory implements TileSelectorFactory {

    public AnyTileSelectorFactory() {
        // nothing to do here
    }

    @Override
    public TileSelector makeSelector(EffectVisitor visitor) {
        return new AnyTileSelector();
    }
}
