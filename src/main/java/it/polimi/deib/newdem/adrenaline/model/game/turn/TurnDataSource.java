package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionDataSource;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

public interface TurnDataSource extends ActionDataSource {

    ActionType requestAction(List<ActionType> actionTypeList) throws UndoException;

    void pushActor(Player actor);

    void popActor(Player actor);

    Player peekActor();

}
