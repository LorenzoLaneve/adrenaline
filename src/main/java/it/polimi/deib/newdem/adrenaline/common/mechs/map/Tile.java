package it.polimi.deib.newdem.adrenaline.common.mechs.map;

import java.util.List;
import it.polimi.deib.newdem.adrenaline.common.mechs.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.WeaponSet;

public interface Tile {

    Map getMap();

    Room getRoom();

    TilePosition getPosition();

    int distanceFrom(Tile t);

    List<Player> getPlayers();

    List<Tile> getAdjacents();

    boolean hasSpawnPoint();

    DropInstance getDrops();
    /*
    At the time of writing, DropInstance hasn't been intrduced
    not even as a stub

    TODO validate
     */

    WeaponSet getWeapons();
    /*
    At the time of writing, WeaponSet hasn't been intrduced
    not even as a stub

    TODO validate
     */
}
