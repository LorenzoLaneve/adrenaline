package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.MapData;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

import java.util.List;

public interface MapView {

    /**
     * Updates the view with the given map data.
     */
    void updateView(MapData data);

    /**
     * Notifies that the given drops appeared in the given location.
     * @param tile The location of the tile where the drops appeared.
     * @see GameData.DropType for possible values to give to drop1, drop2 and drop3.
     */
    void addDrops(TilePosition tile, GameData.DropType drop1, GameData.DropType drop2, GameData.DropType drop3);

    /**
     * Notifies that the player with the given color moved to the given location.
     */
    void movePlayer(PlayerColor player, TilePosition destTile);

    /**
     * Notifies that the player with the given color spawned in the map at the given location.
     */
    void spawnPlayer(PlayerColor player, TilePosition tile);

    /**
     * Notifies that the player with the given color has been killed.
     */
    void killPlayer(PlayerColor player);

    /**
     * Notifies that the player with the given color has to be removed from the map.
     */
    void removePlayer(PlayerColor player);

    void addWeapon(TilePosition tilePosition, int cardId);

    void acquireDrop(TilePosition tile, PlayerColor player, GameData.DropType drop1, GameData.DropType drop2, GameData.DropType drop3);

}
