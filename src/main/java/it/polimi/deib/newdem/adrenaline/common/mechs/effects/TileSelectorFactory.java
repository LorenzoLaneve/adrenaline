package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

public interface TileSelectorFactory {
    TileSelector makeSelector(EffectVisitor visitor);
}
