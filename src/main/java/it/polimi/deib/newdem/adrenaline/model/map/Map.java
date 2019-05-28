package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

public interface Map {

    List<Room> getRooms();

    Tile getTile(TilePosition p);

    void bindRooms();

    void movePlayer(Player player, Tile destination);

    void setListener(MapListener listener);

    MapListener getListener();

    void removePlayer(Player player);

    static Map createMap(String mapJsonData){

        return new MapBuilder(mapJsonData).buildMap();
    }

}
