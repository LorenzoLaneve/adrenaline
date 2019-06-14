package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class RegisterUsernameEvent implements UserEvent {

    private boolean accepted;

    public RegisterUsernameEvent(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean usernameAccepted() {
        return accepted;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
