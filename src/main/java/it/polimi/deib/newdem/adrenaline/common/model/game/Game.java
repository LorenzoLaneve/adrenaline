package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.model.map.Map;

public interface Game {

    Map getMap();

    Player getPlayer(PlayerColor color);

    boolean isInFrenzy();

    void makeTurn();
}
