package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class MockConnectionSender implements UserConnection {
    @Override
    public void start() {

    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void addReceiver(UserConnectionReceiver receiver) {

    }

    @Override
    public void removeReceiver(UserConnectionReceiver receiver) {

    }

    @Override
    public void sendEvent(UserEvent event) {

    }

    @Override
    public void close() {

    }
}
