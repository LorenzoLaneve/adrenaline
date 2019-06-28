package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.Lobby;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

/**
 * A lobby controller object managing the life cycle of a lobby.
 */
public interface LobbyController {

    /**
     * The lobby object managed by this controller.
     */
    Lobby getLobby();

    /**
     * Adds the user to the managed lobby.
     * The state of the lobby may change properly according to the new number of users.
     * Also all the users in the lobby will be notified of the new user.
     */
    void addUser(User user);

    /**
     * Removes the user from the managed lobby.
     * The state of the lobby may change properly according to the new number of users.
     * Also all the users in the lobby will be notified of the change.
     */
    void removeUser(User user);

    /**
     * Returns the timer listener awaiting for the lobby timer countdown events associated to the managed lobby.
     */
    TimerListener getTimerListener();

    /**
     * Returns whether the lobby controller accepts new users, according to its state.
     */
    boolean acceptsNewUsers();

    /**
     * Returns the configuration object used by the lobby controller to parameters such as timer length and bounds for
     * number of players.
     */
    Config getConfig();

    /**
     * Starts the game in the lobby managed by this controller, according to the associated configuration file.
     */
    void startGame();

    /**
     * Ends the game and disconnects all the users in the lobby.
     */
    void endGame();

}
