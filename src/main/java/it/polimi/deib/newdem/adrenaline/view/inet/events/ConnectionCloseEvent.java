package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The connection was closed from the remote host.
 * @see UserEvent to see what this class is used for.
 */
public class ConnectionCloseEvent implements UserEvent {

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
