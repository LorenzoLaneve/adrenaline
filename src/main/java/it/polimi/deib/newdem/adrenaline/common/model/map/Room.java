package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

import java.util.List;

public interface Room {

    List<Tile> getTiles();

    Map getMap();

    // discussed on Discord
    List<Player> getPlayers();

    void addTiles(Tile tile);

    void setMap(Map map);
}
