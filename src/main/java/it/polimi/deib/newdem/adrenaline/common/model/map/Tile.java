package it.polimi.deib.newdem.adrenaline.common.model.map;

import java.util.List;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.common.model.items.WeaponSet;

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
