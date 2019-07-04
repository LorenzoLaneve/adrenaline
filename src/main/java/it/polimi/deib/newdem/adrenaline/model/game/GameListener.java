package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public interface GameListener {

    /**
     * Notifies that the game has been initialized and sends the current state of the game in terms of players.
     */
    void gameDidInit(Game game, GameData gameData);

    /**
     * Notifies that the game is about to be over.
     */
    void gameWillEnd(Game game, GameResults gameResults);

    /**
     * Notifies that the given user/player has reconnected to the game.
     */
    void userDidEnterGame(User user, Player player);

    /**
     * Notifies that the given user/player has disconnected from the game.
     */
    void userDidExitGame(User user, Player player);

    void playerRestoredMatchData(Game game, Player player);

}
