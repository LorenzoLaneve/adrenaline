package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

public interface UserEvent {


    /**
     * Notifies the user connection receiver object of the event by calling the appropriate method.
     */
    void notifyEvent(UserConnection connection, UserConnectionReceiver receiver);


    /**
     * Sends the event using the given connection sender.
     */
    void sendEvent(UserConnectionSender sender) throws ConnectionException;

}
