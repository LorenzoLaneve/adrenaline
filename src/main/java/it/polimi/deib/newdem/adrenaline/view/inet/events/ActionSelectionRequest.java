package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.ArrayList;
import java.util.List;

public class ActionSelectionRequest implements UserEvent {

    private ArrayList<ActionType> actionTypes;

    public ActionSelectionRequest(List<ActionType> actionTypeList) {
        this.actionTypes = new ArrayList<>(actionTypeList);
    }

    public List<ActionType> getChoices() {
        return new ArrayList<>(actionTypes);
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
