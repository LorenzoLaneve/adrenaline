package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

public interface Room {

    List<Tile> getTiles();

    Map getMap();

    List<Player> getPlayers();

    void addTiles(Tile tile);

    /**
     * Used because the Room is initialized before the map.
     * @param map
     */
    void setMap(Map map);
}
