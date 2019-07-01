package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;

import java.util.List;

public interface Map {

    List<Room> getRooms();

    Tile getTile(TilePosition p);

    /**Used to set the map of the rooms in rooms since they are created
     * before the Map to which they belong is initialised. It should always be called right after the constructor.
     */
    void bindRooms();

    void movePlayer(Player player, Tile destination);

    void setListener(MapListener listener);

    MapListener getListener();

    void removePlayer(Player player);

    /**
     * Used to extract selectable tiles in the map based on the selector.
     * @param selector the conditions the tiles have to meet.
     * @return the tile that meet the condition.
     */
    List<Tile> selectTiles(TileSelector selector);

    Tile getSpawnPointFromColor(AmmoColor ammoColor);

    List<Tile> getAllTiles();

    static Map createMap(String mapJsonData){

        return new MapBuilder(mapJsonData).buildMap();
    }

    int getDistance(Tile source, Tile destination);

    /**
     * @return Generate the MapData used to update the views.
     */
    MapData generateMapData();

    String getMapID();

    List<Player> getPlayers();

    TilePosition getPlayerPosition(Player player);

    /**
     * sends through the listener the MapData used to update the views.
     */
    void sendMapData();

    void updatePlayerState(Player player, boolean alive);

}
