package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public final class NullKillTrackLister implements KillTrackListener {

    @Override
    public void killTrackDidRestore(KillTrackData data) {
        // nothing to do here.
    }

    @Override
    public void playerDidKill(Player player, int amount) {
        // nothing to do here.
    }

    @Override
    public void killTrackDidUndoLastKill() {
        // nothing to do here.
    }

}
