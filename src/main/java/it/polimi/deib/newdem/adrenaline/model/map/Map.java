package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.Player;

import java.util.List;

public interface Map {

    List<Room> getRooms();

    Tile getTile(TilePosition p);

    void bindRooms();

    void movePlayer(Player player, Tile destination);

}
