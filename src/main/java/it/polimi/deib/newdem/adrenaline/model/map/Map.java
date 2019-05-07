package it.polimi.deib.newdem.adrenaline.model.map;

import java.util.List;

public interface Map {

    List<Room> getRooms();

    Tile getTile(TilePosition p);

    void bindRooms();

}
