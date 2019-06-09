package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class RejectUsernameEvent implements UserEvent {

    private String name;

    public RejectUsernameEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
