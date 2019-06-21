package it.polimi.deib.newdem.adrenaline.view;

public interface LobbyView {

    /**
     * Notifies that a new user with the given name entered the lobby.
     */
    void addUser(String name);

    /**
     * Notifies that the user with the given name left the lobby.
     */
    void removeUser(String name);

    /**
     * Notifies that a timer is started, with the given start of the countdown.
     */
    void startTimer(int seconds);

    /**
     * Updates the timer with the given left seconds.
     */
    void syncTimer(int seconds);

    /**
     * Notifies that the timer in the lobby has been aborted.
     */
    void abortTimer();

}
