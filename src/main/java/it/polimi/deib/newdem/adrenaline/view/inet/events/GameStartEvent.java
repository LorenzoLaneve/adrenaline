package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * A game will start/resume in the lobby.
 * @see UserEvent to see what this class is used for.
 */
public class GameStartEvent implements UserEvent {

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
