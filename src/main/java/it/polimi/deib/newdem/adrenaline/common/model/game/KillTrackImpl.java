package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.model.game.KillTrack;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

import java.util.List;

public class KillTrackImpl implements KillTrack {

    private List<Player> killers;
    private int trackLength;

    public KillTrackImpl(int trackLength){
        //TODO
    }

    @Override
    public Player getKiller(int killIndex) {
        //TODO
        return null;
    }

    @Override
    public int getTrackLength() {
        //TODO
        return 0;
    }
}
