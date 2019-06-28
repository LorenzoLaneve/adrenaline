package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.List;

/**
 * Object that manages a game's life cycle, it's setup and its teardown.
 * Note that this interface is agnostics to the specific game that can be hosted by the server.
 * {@see GameControllerFactory} to see how to create a game controller.
 */
public interface GameController {

    /**
     * Initializes a new game with the given list of users.
     */
    void setupGame(List<User> users);

    /**
     * Recovers a game from the server's disk in order to restore it.
     * Note that this is not used yet.
     */
    void recoverGame();

    /**
     * Starts the game's life cycle.
     * This method has to return only when the game is declared over.
     */
    void runGame();

    /**
     * Notifies the game controller that the given user has disconnected from the hosting lobby.
     */
    void userDidDisconnect(User user);

    /**
     * Notifies the game controller that the given user has reconnected from the hosting lobby.
     */
    void userDidReconnect(User user);

}
