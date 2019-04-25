package it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.events;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.inet.UserConnectionSender;

public class UpdateUsernameEvent implements UserEvent {

    private String newUsername;

    /**
     * Initializes a new username update event.
     * @param newUsername The new username that has to be set.
     */
    public UpdateUsernameEvent(String newUsername) {
        this.newUsername = newUsername;
    }

    /**
     * Returns the new username that has to be set for the user that generated this event.
     */
    public String getNewUsername() {
        return newUsername;
    }


    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.userDidUpdateUsername(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendUpdateUsernameEvent(this);
    }

}
