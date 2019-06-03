package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.TimeoutException;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;

import java.util.List;

public class NullTurnDataSource implements TurnDataSource {
    @Override
    public ActionType chooseAction(List<ActionType> actionTypeList) {
        return null;
    }
}
