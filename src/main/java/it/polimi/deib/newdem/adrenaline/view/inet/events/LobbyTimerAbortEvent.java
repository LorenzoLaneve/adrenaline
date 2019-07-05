package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The timer in the lobby has been aborted.
 * @see UserEvent to see what this class is used for.
 */
public class LobbyTimerAbortEvent implements UserEvent {

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
