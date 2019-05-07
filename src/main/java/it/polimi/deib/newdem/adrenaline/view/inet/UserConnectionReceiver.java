package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.view.inet.events.EnterLobbyEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UpdateUsernameEvent;

public interface UserConnectionReceiver {


    /**
     * The user sent an update username event.
     * @param connection the connection that received the event.
     * @param event an {@code UpdateUsernameEvent} object containing all the information about the event.
     */
    void userDidUpdateUsername(UserConnection connection, UpdateUsernameEvent event);

    /**
     * The server.
     * @param connection the connection that received the event.
     * @param event an {@code EnterLobbyEvent} object containing all the information about the event.
     */
    void serverDidAssignLobby(UserConnection connection, EnterLobbyEvent event);


    /**
     * Notifies that a connection has been closed, either by the local or the remote host.
     * @param connection the connection that has been closed.
     */
    void connectionDidClose(UserConnection connection);

}
