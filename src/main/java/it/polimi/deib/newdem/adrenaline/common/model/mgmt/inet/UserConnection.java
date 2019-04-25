package it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.events.UserEvent;

public interface UserConnection {


    /**
     * Starts a new thread that listens to new events.
     * Any new event that will arrive to the object will be notified on all the receivers within that thread.
     */
    void start();

    /**
     * Returns the user associated to the connection.
     */
    User getUser();

    /**
     * Adds the given receiver, making him listen to future events.
     */
    void addReceiver(UserConnectionReceiver receiver);

    /**
     * Removes the given receiver, so that it will not receive any further event.
     */
    void removeReceiver(UserConnectionReceiver receiver);

    /**
     * Sends the given {@code UserEvent} to the other end of the connection.
     */
    void sendEvent(UserEvent event);

    /**
     * Closes the connection, freeing all the allocated resources.
     */
    void close();



}
