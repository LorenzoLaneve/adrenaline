package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionDataSource;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.util.List;

public interface TurnDataSource extends ActionDataSource {

    ActionType chooseAction(List<ActionType> actionTypeList) throws UndoException;

    PowerUpCard chooseCard(List<PowerUpCard> cards) throws UndoException;

    void turnDidStart(Player actor);

}
