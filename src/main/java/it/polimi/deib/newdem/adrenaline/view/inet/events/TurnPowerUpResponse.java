package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class TurnPowerUpResponse implements UserEvent {

    private TurnView.ValOrUndo<Integer> value;

    public TurnPowerUpResponse(TurnView.ValOrUndo<Integer> value) {
        this.value = value;
    }

    public TurnView.ValOrUndo<Integer> getValue() {
        return value;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
