package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class NearTileSelector implements TileSelector {

    private Tile sourceTile;
    private int minDistance;
    private int maxDistance;

    /**
     * Selects all the tiles in the given distance range from the given tile.
     * @see TileSelector for further information
     */
    public NearTileSelector(Tile sourceTile, int minDist, int maxDist){
        this.sourceTile = sourceTile;
        this.minDistance = minDist;
        this.maxDistance = maxDist;
    }

    public NearTileSelector(Player sourcePlayer, int minDist, int maxDist) {
        this(sourcePlayer.getTile(), minDist, maxDist);
    }

    @Override
    public boolean isSelectable(Map map, Tile tile) {
        int distance = sourceTile.distanceFrom(tile);

        return minDistance <= distance && distance <= maxDistance;
    }
}
