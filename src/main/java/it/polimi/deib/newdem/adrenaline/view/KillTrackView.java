package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

public interface KillTrackView {

    /**
     * Notifies a new kill made by the player with the given color.
     */
    void registerKill(PlayerColor pColor, int amount);

    /**
     * Notifies that the last kill was undone by the kill track.
     */
    void undoLastKill();

}
