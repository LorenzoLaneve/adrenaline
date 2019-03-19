package it.polimi.deib.newdem.adrenaline.common.mechs.map;

import java.util.List;

public interface Map {

    List<Room> getRoom();

    Tile getTile(TilePosition p);

}
