package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

/**
 * A tile selector defines a predicate that discriminates tiles by different criteria.
 */
public interface TileSelector {
    /**
     * Method used to check whether the tile meets the condition of the selector.
     * @param map context of the selector.
     * @param tile the Tile object that should be checked by this selector.
     * @return whether the player meets the condition of the selector.
     */
    boolean isSelectable(Map map, Tile tile);
}
