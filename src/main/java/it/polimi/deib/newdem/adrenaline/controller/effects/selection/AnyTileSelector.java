package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class AnyTileSelector implements TileSelector {

    /**
     * Selects all the tiles.
     * @see TileSelector for further information
     */
    public AnyTileSelector() {
        // nothing to do here
    }

    @Override
    public boolean isSelectable(Map map, Tile tile) {
        return true;
    }
}
