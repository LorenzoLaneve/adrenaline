package it.polimi.deib.newdem.adrenaline.common.model.map;

import java.util.List;

public interface Map {

    List<Room> getRooms();

    Tile getTile(TilePosition p);

    void generateRooms();

}
