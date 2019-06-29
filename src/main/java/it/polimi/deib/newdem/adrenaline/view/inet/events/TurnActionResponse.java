package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class TurnActionResponse implements UserEvent {

    private TurnView.ValOrUndo<ActionType> value;

    public TurnActionResponse(TurnView.ValOrUndo<ActionType> value) {
        this.value = value;
    }

    public TurnView.ValOrUndo<ActionType> getValue() {
        return value;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
