package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

/**
 * A view that observes a KillTrack object in the model.
 */
public interface KillTrackView {

    /**
     * Restores the view setting its initial state to the given data state sent by the model.
     */
    void restoreView(KillTrackData data);

    /**
     * Notifies a new kill made by the player with the given color.
     */
    void registerKill(PlayerColor pColor, int amount);

    /**
     * Notifies that the last kill was undone by the kill track.
     */
    void undoLastKill();

}
