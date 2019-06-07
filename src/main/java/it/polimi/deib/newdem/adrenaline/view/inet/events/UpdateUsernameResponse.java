package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class UpdateUsernameResponse implements UserEvent {

    private String newUsername;

    /**
     * Initializes a new username update event.
     * @param newUsername The new username that has to be set.
     */
    public UpdateUsernameResponse(String newUsername) {
        this.newUsername = newUsername;
    }

    /**
     * Returns the new username that has to be set for the user that generated this event.
     */
    public String getNewUsername() {
        return newUsername;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
