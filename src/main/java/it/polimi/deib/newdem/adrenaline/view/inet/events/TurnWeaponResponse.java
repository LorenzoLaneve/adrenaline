package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class TurnWeaponResponse implements UserEvent {

    private TurnView.ValOrUndo<Integer> value;

    public TurnWeaponResponse(TurnView.ValOrUndo<Integer> value) {
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
