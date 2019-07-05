package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * The server is requesting the user to choose a card fragment among the given ones.
 * @see UserEvent to see what this class is used for.
 */
public class TurnWeaponRequest implements UserEvent {

    private ArrayList<Integer> cardIDs;

    public TurnWeaponRequest(List<Integer> cardIDs) {
        this.cardIDs = new ArrayList<>(cardIDs);
    }

    public List<Integer> getAvailableCardIDs() {
        return new ArrayList<>(cardIDs);
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
