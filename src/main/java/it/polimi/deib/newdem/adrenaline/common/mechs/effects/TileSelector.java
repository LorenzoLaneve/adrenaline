package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.map.Map;
import it.polimi.deib.newdem.adrenaline.common.mechs.map.Tile;

public interface TileSelector {
    boolean isSelectable(Map map, Tile tile);
}
