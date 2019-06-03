package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;

import java.util.List;

public interface TurnDataSource {

    ActionType chooseAction(List<ActionType> actionTypeList);

}
