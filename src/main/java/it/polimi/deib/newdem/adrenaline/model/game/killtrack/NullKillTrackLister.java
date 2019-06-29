package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public final class NullKillTrackLister implements KillTrackListener {
    @Override
    public void killTrackDidUpdate(KillTrackData data) {

    }

    @Override
    public void playerDidKill(Player player, int amount) {

    }

    @Override
    public void killTrackDidUndoLastKill() {

    }
}
