package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

public interface GameView {

    /**
     * Notifies that the player with the given color disconnected from the enclosing lobby.
     */
    void disablePlayer(PlayerColor color);

    /**
     * Notifies that the player with the given color reconnected from the enclosing lobby.
     */
    void enablePlayer(PlayerColor color);

}
