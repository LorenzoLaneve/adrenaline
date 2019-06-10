package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class ActionSelectionEvent implements UserEvent {

    private ActionType actionType;

    public ActionSelectionEvent(ActionType actionType) {
        this.actionType = actionType;
    }

    public ActionType getSelectedActionType() {
        return actionType;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
