package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class SameRoomTileSelector implements TileSelector {

    private Tile sourceTile;

    /**
     * Selects all the tiles that are in the same room of the given tile.
     * @see TileSelector for further information
     */
    public SameRoomTileSelector(Tile sourceTile) {
        this.sourceTile = sourceTile;
    }

    /**
     * Selects all the tiles that are in the same room of the given player.
     * @see TileSelector for further information
     */
    public SameRoomTileSelector(Player sourcePlayer) {
        this(sourcePlayer.getTile());
    }

    @Override
    public boolean isSelectable(Map map, Tile tile) {
        return tile.getRoom() == sourceTile.getRoom();
    }
}
