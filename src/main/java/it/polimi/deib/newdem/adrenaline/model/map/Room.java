package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

public interface Room {

    List<Tile> getTiles();

    Map getMap();

    // discussed on Discord
    List<Player> getPlayers();

    void addTiles(Tile tile);

    void setMap(Map map);
}
