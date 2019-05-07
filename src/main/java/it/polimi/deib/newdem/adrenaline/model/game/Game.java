package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.map.Map;

public interface Game {

    Map getMap();

    Player getPlayer(PlayerColor color);

    boolean isInFrenzy();

    void makeTurn();
}
