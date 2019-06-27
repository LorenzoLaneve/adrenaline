package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class WeaponReloadEvent implements UserEvent {

    private int cardID;

    public WeaponReloadEvent(int cardID) {
        this.cardID = cardID;
    }

    public int getCardID() {
        return cardID;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }
}
