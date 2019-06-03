package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;

public class MockSelector implements TileSelector {
    @Override
    public boolean isSelectable(Map map, Tile tile) {
        Tile accTile = map.getTile(new TilePosition(0,0));

        return tile.equals(accTile);
    }
}
