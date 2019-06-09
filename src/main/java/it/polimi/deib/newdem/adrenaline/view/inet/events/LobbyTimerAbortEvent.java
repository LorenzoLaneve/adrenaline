package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;


public class LobbyTimerAbortEvent implements UserEvent {

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
