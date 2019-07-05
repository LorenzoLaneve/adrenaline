package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The server is requesting the user to choose a card fragment among the given ones.
 * if {@code forceChoice} is true, then a {@code null} will not be accepted from the server.
 * @see UserEvent to see what this class is used for.
 */
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
