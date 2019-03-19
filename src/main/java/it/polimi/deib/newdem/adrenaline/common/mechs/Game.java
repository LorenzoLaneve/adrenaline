package it.polimi.deib.newdem.adrenaline.common.mechs;

import it.polimi.deib.newdem.adrenaline.common.mechs.map.Map;

public interface Game {

    Map getMap();

    Player getPlayer(PlayerColor color);

    boolean isInFrenzy();
}
