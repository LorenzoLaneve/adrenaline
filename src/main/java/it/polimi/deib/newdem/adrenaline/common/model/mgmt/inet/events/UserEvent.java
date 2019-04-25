package it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.events;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnectionSender;

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
