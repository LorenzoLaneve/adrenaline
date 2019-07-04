package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.GameResults;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

public interface GameView {

    /**
     * Passes the data about the current state of the game.
     */
    void setGameData(GameData data);

    /**
     * Notifies that the player with the given color disconnected from the enclosing lobby.
     */
    void disablePlayer(PlayerColor color);

    /**
     * Notifies that the player with the given color reconnected from the enclosing lobby.
     */
    void enablePlayer(PlayerColor color);

    /**
     * Notifies that the game is over, with the given results.
     */
    void endGame(GameResults results);

}
