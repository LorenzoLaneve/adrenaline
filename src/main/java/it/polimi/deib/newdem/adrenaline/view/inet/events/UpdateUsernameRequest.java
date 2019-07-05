package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The server requested the client to provide a username.
 * @see UserEvent to see what this class is used for.
 */
public class UpdateUsernameRequest implements UserEvent {

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
