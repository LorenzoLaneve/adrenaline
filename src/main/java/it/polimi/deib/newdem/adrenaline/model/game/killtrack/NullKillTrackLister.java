package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * A null object to allow testing and default values.
 *
 * All of this methods do not report to anything.
 */
public final class NullKillTrackLister implements KillTrackListener {

    /**
     * This method does not report to anything
     */
    @Override
    public void killTrackDidRestore(KillTrackData data) {
        // nothing to do here.
    }

    /**
     * This method does not report to anything
     */
    @Override
    public void playerDidKill(Player player, int amount) {
        // nothing to do here.
    }

}
