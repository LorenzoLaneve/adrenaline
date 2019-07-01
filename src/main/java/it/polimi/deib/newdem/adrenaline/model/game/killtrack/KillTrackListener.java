package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public interface KillTrackListener {

    /**
     * Used both for first initialization and to restore KillTrackData to disconnected users.
     * @param data
     */
    void killTrackDidRestore(KillTrackData data);

    /**
     * @param player the killer.
     * @param amount of markers to put on current cell of the killtrack, if the killer only killed amount is 1, if the killer went overkill on his victim amount is 2
     */
    void playerDidKill(Player player, int amount);

    void killTrackDidUndoLastKill();

}
