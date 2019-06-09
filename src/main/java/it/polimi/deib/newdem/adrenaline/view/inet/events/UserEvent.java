package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.io.Serializable;

public interface UserEvent extends Serializable {

    /**
     * Notifies all the subscribers to this event registered in the given user connection object of this event.
     */
    void publish(UserConnection connection);

}
