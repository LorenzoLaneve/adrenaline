package it.polimi.deib.newdem.adrenaline.common.controller.effects;

public interface TileSelectorFactory {
    TileSelector makeSelector(EffectVisitor visitor);
}
