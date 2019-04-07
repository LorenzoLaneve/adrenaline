package it.polimi.deib.newdem.adrenaline.common.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.common.model.map.Map;
import it.polimi.deib.newdem.adrenaline.common.model.map.Tile;

public interface TileSelector {
    boolean isSelectable(Map map, Tile tile);
}
