package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class RejectUsernameEvent implements UserEvent {

    private String name;

    public RejectUsernameEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.serverDidRejectUsername(connection, this);
    }

}
