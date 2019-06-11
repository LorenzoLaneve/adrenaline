package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.ArrayList;
import java.util.List;

public class PowerUpCardSelectionRequest implements UserEvent {

    private ArrayList<PowerUpCard> cards;

    public PowerUpCardSelectionRequest(List<PowerUpCard> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<PowerUpCard> getCards() {
        return new ArrayList<>(cards);
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
