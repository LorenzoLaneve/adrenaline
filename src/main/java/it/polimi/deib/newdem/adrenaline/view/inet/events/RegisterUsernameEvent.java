package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The server received the username sent by the client.
 * {@code usernameAccepted()} will say if the server accepted the name or not.
 * @see UserEvent to see what this class is used for.
 */
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
