package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.util.List;

public interface TurnDataSource {

    ActionType chooseAction(List<ActionType> actionTypeList);

    PowerUpCard chooseCard(List<PowerUpCard> cards);

}
