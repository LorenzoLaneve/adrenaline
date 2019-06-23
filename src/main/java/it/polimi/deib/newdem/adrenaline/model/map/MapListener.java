package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;


public interface MapListener {

    void playerDidMove(Player player, Tile source, Tile destination);

    void playerDidSpawn(Player player, Tile spawnPoint);

    void dropDidSpawn(Tile tile, DropInstance drop);

    void playerDidDie(Player player);

    void playerDidResurrect(Player player);

    void playerDidLeaveMap(Player player);

    void mapDidRestoreData(MapData data);

    void weaponDidSpawn(Tile tile, WeaponCard weapon);

    void playerDidGrabDrop(Player player, DropInstance drop, Tile tile );


    // TODO methods definition for other map events.


}
