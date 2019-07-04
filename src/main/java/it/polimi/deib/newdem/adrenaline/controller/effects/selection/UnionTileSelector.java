package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class UnionTileSelector implements TileSelector {

    private TileSelector firstOp;
    private TileSelector secondOp;

    /**
     * Selects all the tiles that are selected by one of the given player selectors.
     * @see TileSelector for further information
     */
    public UnionTileSelector(TileSelector firstOp, TileSelector secondOp) {
        this.firstOp = firstOp;
        this.secondOp = secondOp;
    }

    @Override
    public boolean isSelectable(Map map, Tile tile) {
        return firstOp.isSelectable(map, tile) || secondOp.isSelectable(map, tile);
    }
}
