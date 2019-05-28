package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackListener;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.NullKillTrackLister;

public class ListenerRegistry {

    private KillTrackListener ktl;
    private GameListener gameListener;

    public ListenerRegistry() {
        ktl = new NullKillTrackLister();
        gameListener = new NullGameListener();
    }

    public GameListener getGameListener() {
        return gameListener;
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }

    public void setKillTrackListener(KillTrackListener killTrackListener) {
        ktl = killTrackListener;
    }

    KillTrackListener getKillTrackListener() {
        return ktl;
    }
}
