package it.polimi.deib.newdem.adrenaline.common.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.EffectVisitor;

public interface TileSelectorFactory {
    TileSelector makeSelector(EffectVisitor visitor);
}
