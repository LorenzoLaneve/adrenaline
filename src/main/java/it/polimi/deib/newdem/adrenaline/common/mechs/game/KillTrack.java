package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;

public interface KillTrack {

    Player getKiller(int killIndex);

    int getTrackLength();

}
