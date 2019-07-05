package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.io.Serializable;

/**
 * Serializable object exchanged between {@code UserConnection} objects. Any class implementing this interface can
 * be added, providing different semantics to the exchanged messages, and any type of data can be delivered by
 * encapsulating them in these classes.
 */
public interface UserEvent extends Serializable {

    /**
     * Notifies all the subscribers to this event registered in the given user connection object of this event.
     */
    void publish(UserConnection connection);

}
