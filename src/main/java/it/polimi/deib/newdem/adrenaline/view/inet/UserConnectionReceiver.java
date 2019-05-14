package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.view.inet.events.EnterLobbyEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.ExitLobbyEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.LobbyTimerUpdateEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UpdateUsernameEvent;

public interface UserConnectionReceiver {


    /**
     * The user sent an update username event.
     * @param connection the connection that received the event.
     * @param event an {@code UpdateUsernameEvent} object containing all the information about the event.
     */
    void userDidUpdateUsername(UserConnection connection, UpdateUsernameEvent event);

    /**
     * A new user entered the lobby.
     * @param connection the connection that received the event.
     * @param event an {@code EnterLobbyEvent} object containing all the information about the event.
     */
    void userDidEnterLobby(UserConnection connection, EnterLobbyEvent event);

    /**
     * A user left the lobby.
     * @param connection the connection that received the event.
     * @param event an {@code ExitLobbyEvent} object containing all the information about the event.
     */
    void userDidExitLobby(UserConnection connection, ExitLobbyEvent event);

    /**
     * The lobby timer has been updated.
     * @param connection the connection that received the event.
     * @param event an {@code LobbyTimerUpdateEvent} object containing all the information about the event.
     */
    void lobbyDidUpdateTimer(UserConnection connection, LobbyTimerUpdateEvent event);


    /**
     * Notifies that a connection has been closed, either by the local or the remote host.
     * @param connection the connection that has been closed.
     */
    void connectionDidClose(UserConnection connection);

}
