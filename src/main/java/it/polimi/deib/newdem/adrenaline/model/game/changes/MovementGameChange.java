package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

/**
 * GameChange object that moves the given player to the given Tile.
 */
public class MovementGameChange implements GameChange {

    private Player player;

    private Tile destinationTile;

    private Tile sourceTile;

    /**
     * Builds a new {code GameChange} to move the given {@code Player} to the specified {@code TIle }
     * @param player player to move
     * @param destTile destination of the movement
     */
    public MovementGameChange(Player player, Tile destTile) {
        this.player = player;
        this.destinationTile = destTile;
    }

    @Override
    public void update(Game game) {
        sourceTile = player.getTile();

        sourceTile.getMap().movePlayer(player, destinationTile);
    }

    @Override
    public void revert(Game game) {
        if (sourceTile != null) {
            sourceTile.getMap().movePlayer(player, sourceTile);
        }
    }
}
