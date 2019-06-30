package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.ArrayList;
import java.util.List;

public class TurnActionRequest implements UserEvent {

    private ArrayList<ActionType> availableActions;

    public TurnActionRequest(List<ActionType> availableActions) {
        this.availableActions = new ArrayList<>(availableActions);
    }

    public List<ActionType> getAvailableActions() {
        return new ArrayList<>(availableActions);
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
