package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public interface Game {

    Map getMap();

    Player getPlayerFromColor(PlayerColor color);

    boolean isInFrenzy();

    boolean shouldGoFrenzy();

    Turn getNextTurn();

    void concludeTurn(Turn turn);

    void setUserForColor(User user, PlayerColor color);

    User getUserByPlayer(Player player);

    void init();

}
