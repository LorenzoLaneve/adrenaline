package it.polimi.deib.newdem.adrenaline.common.mechs;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;

public interface Turn {

    Player getActivePlayer();

    void start();

}
