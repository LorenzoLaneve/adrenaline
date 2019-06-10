package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class PowerUpCardSelectionEvent implements UserEvent {

    private int cardID;

    public PowerUpCardSelectionEvent(int cardID) {
        this.cardID = cardID;
    }

    public int getSelectedCardID() {
        return cardID;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
