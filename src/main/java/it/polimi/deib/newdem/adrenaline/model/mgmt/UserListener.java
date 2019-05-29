package it.polimi.deib.newdem.adrenaline.model.mgmt;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public interface UserListener {

    /**
     * Notifies that the given user has switched from the {@code oldConnection} to the {@code newConnection}.
     */
    void userDidChangeConnection(User user, UserConnection oldConnection, UserConnection newConnection);

    /**
     * Notifies that the given user has changed name.
     */
    void userDidChangeName(User user, String name);

}
