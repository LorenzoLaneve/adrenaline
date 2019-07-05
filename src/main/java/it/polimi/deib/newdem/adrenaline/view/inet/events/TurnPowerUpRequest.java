package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * The server is requesting the user to choose a power up among the given ones.
 * @see UserEvent to see what this class is used for.
 */
public class TurnPowerUpRequest implements UserEvent {

    private final ArrayList<Integer> cardIDs;

    public TurnPowerUpRequest(List<Integer> cardIDs) {
        this.cardIDs = new ArrayList<>(cardIDs);
    }

    public List<Integer> getAvailableCards() {
        return cardIDs;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
