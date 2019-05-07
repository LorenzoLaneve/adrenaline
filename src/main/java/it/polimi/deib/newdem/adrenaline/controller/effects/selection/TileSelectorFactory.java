package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;

public interface TileSelectorFactory {
    TileSelector makeSelector(EffectVisitor visitor);
}
