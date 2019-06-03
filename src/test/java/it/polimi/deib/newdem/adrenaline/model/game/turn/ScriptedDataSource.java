package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.Action;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScriptedDataSource implements TurnDataSource {

    ActionType[] arr;
    int i;

    public ScriptedDataSource(ActionType ... types) {
        arr = types;
        i = 0;
    }

    @Override
    public ActionType chooseAction(List<ActionType> actionTypeList) {
        ActionType out = arr[i];
        i++;
        return out;
    }
}
