package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * A user with the given username entered the lobby.
 * @see UserEvent to see what this class is used for.
 */
public class EnterLobbyEvent implements UserEvent {

    private String username;

    /**
     * Create a new enter lobby event indicating that the user with the specified username entered the lobby (server bound event)
     * @param username The name provided by the new user.
     */
    public EnterLobbyEvent(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
