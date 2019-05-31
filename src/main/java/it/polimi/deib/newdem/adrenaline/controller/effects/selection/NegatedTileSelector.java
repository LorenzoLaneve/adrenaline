package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class NegatedTileSelector implements TileSelector {

    private TileSelector innerSelector;

    public NegatedTileSelector(TileSelector innerSelector) {
        this.innerSelector = innerSelector;
    }

    @Override
    public boolean isSelectable(Map map, Tile tile) {
        return !innerSelector.isSelectable(map, tile);
    }
}
