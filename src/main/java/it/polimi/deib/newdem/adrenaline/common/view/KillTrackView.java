package it.polimi.deib.newdem.adrenaline.common.view;

import it.polimi.deib.newdem.adrenaline.common.model.game.PlayerColor;

public interface KillTrackView {

    /**
     * Notifies a new kill made by the player with the given color.
     */
    void registerKill(PlayerColor pColor);

    /**
     * Notifies that the game passed to frenzy mode.
     */
    void goFrenzy();

}
