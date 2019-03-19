package it.polimi.deib.newdem.adrenaline.common.mechs.map;

import it.polimi.deib.newdem.adrenaline.common.mechs.Player;

import java.util.List;

public interface Room {

    List<Tile> getTiles();

    Map getMap();

    // discussed on Discord
    Player getPlayers();
}
