package it.polimi.deib.newdem.adrenaline.common.mechs.map;

import java.util.List;
import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.WeaponSet;

public interface Tile {

    Map getMap();

    Room getRoom();

    TilePosition getPosition();

    int distanceFrom(Tile t);

    List<Player> getPlayers();

    List<Tile> getAdjacentTiles();

    boolean hasSpawnPoint();

    DropInstance getDrops();

    WeaponSet getWeapons();
}
