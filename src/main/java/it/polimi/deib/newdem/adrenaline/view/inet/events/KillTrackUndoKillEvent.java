package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The kill track in the game undid the last damage.
 * @see UserEvent to see what this class is used for.
 */
public class KillTrackUndoKillEvent implements UserEvent {

    @Override
    public void publish(UserConnection connection) {
        connection.sendEvent(this);
    }

}
