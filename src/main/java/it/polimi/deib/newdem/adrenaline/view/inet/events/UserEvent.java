package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

import java.io.Serializable;

public interface UserEvent extends Serializable {

    /**
     * Notifies the user connection receiver object of the event by calling the appropriate method.
     */
    void notifyEvent(UserConnection connection, UserConnectionReceiver receiver);
    
}
