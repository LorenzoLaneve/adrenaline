package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.Player;

import java.util.List;

public interface Map {

    List<Room> getRooms();

    Tile getTile(TilePosition p);

    void bindRooms();

    void movePlayer(Player player, Tile destination);

    void setListener(MapListener listener);

    MapListener getListener();

    void removePlayer(Player player);

    static Map buildMap(Tile[][] matrixMap, List<Room> rooms){
        Map map = new ConcreteMap(matrixMap,rooms);
        map.bindRooms();

        return map;
    }

}
