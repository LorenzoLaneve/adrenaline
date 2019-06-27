package it.polimi.deib.newdem.adrenaline.model.mgmt;

import java.util.List;

public interface Lobby {

    void setListener(LobbyListener listener);

    /**
     * Adds the given user to the lobby.
     */
    void addUser(User user);

    /**
     * Removes the given user from the lobby.
     */
    void removeUser(User user);

    /**
     * Starts the timer in the lobby.
     */
    void startTimer(int secondsLeft);

    /**
     * Updates the seconds left for the countdown in the lobby.
     */
    void refreshTimer(int secondsLeft);

    /**
     * Sets the game started flag for this lobby.
     */
    void startGame();

    /**
     * Aborts the timer in the lobby.
     */
    void abortTimer();

    /**
     * Returns a list of the users that are in the lobby at the moment of the call.
     * Please note that no assumption will be made on the order.
     */
    List<User> getUsers();

    /**
     * Returns whether the lobby has started a game.
     */
    boolean isInGame();

}
