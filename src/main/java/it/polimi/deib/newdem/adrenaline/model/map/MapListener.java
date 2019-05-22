package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.Player;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;


import java.util.List;

public interface MapListener {

    void playerDidMove(Player player, Tile source, Tile destination);

    void playerDidSpawn(Player player, Tile spawnPoint);

    void dropDidSpawn(Tile tile, DropInstance drop);

    void playerDidDie(Player player);

    void playerDidLeaveMap(Player player);

    void mapDidSendTileData(List<Tile> tileData);

    void mapDidSendSpawnPointData(List<Tile> spawnPointTileData);

    void weaponDidSpawn(Tile tile, WeaponCard weapon);

    // TODO methods definition for other map events.


}
