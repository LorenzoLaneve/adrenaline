package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class KillTrackUndoKillEvent implements UserEvent {

    @Override
    public void publish(UserConnection connection) {
        connection.sendEvent(this);
    }

}
