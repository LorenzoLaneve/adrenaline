package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.map.Map;

public interface Game {

    Map getMap();

    Player getPlayerFromColor(PlayerColor color);

    boolean isInFrenzy();

    boolean shouldGoFrenzy();

    Turn getNextTurn();

    void concludeTurn(Turn turn);


}
