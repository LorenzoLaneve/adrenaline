package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;


public interface MapListener {

    void playerDidMove(Player player, Tile source, Tile destination);

    void playerDidSpawn(Player player, Tile spawnPoint);

    void dropDidSpawn(Tile tile, DropInstance drop);

    /**
     * Notify of a grabbed drop.
     * @param tile where the drop was removed.
     */
    void dropDidDespawn(Tile tile);

    void playerDidDie(Player player);

    /**
     * Used when a player is revived due to an UndoException
     * @param player to be resurrected
     */
    void playerDidResurrect(Player player);

    /**
     * Used both when a player dies or disconnects.
     * @param player
     */
    void playerDidLeaveMap(Player player);

    /**
     * Used both for first initialization and to restore MapData to disconnected users.
     * @param data
     */
    void mapDidRestoreData(MapData data);

    void weaponDidSpawn(Tile tile, WeaponCard weapon);

    /**
     * Notify of a grabbed weapon.
     * @param tile the spawnPointTile where the weapon.
     * @param weapon grabbed.
     */
    void weaponDidDespawn(Tile tile, WeaponCard weapon);

}
