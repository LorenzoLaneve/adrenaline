package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;

import java.util.List;

public interface Map {

    List<Room> getRooms();

    Tile getTile(TilePosition p);

    void bindRooms();

    void movePlayer(Player player, Tile destination);

    void setListener(MapListener listener);

    MapListener getListener();

    void removePlayer(Player player);

    List<Tile> selectTiles(TileSelector selector);

    Tile getSpawnPointFromColor(AmmoColor ammoColor);

    List<Tile> getAllTiles();

    static Map createMap(String mapJsonData){

        return new MapBuilder(mapJsonData).buildMap();
    }

    int getDistance(Tile source, Tile destination);

}
