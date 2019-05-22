package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

import java.util.List;

public interface MapView {

    enum DropType {
        RED_AMMO,
        BLUE_AMMO,
        YELLOW_AMMO,
        POWER_UP
    }


    /**
     * Passes all the tiles of the map to the view.
     * @param tileData A list of the positions where there is a tile of the map.
     */
    void setTiles(List<TilePosition> tileData);

    /**
     * Passes all the information about the spawn points in the map. Please note that {@code setTiles()} should be called first,
     * and all the tiles passed from this method have to be contained to the tiles notified through {@code setTiles()}.
     * @param tileData A list of the positions where there is a spawn point of the map.
     */
    void setSpawnPoints(List<TilePosition> tileData);

    /**
     * Notifies that the given drops appeared in the given location.
     * @param tile The location of the tile where the drops appeared.
     * @see MapView.DropType for possible values to give to drop1, drop2 and drop3.
     */
    void addDrops(TilePosition tile, DropType drop1, DropType drop2, DropType drop3);

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


}
