package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * Event that must be sent every 5 seconds to keep the connection alive.
 * @see UserEvent to see what this class is used for.
 */
public class HeartbeatEvent implements UserEvent {

    @Override
    public void publish(UserConnection connection) {
        connection.resetHeartbeat();
    }

}
