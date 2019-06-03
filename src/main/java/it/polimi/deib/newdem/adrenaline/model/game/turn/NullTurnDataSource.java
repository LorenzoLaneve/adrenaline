package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.TimeoutException;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.util.List;

public class NullTurnDataSource implements TurnDataSource {
    @Override
    public PowerUpCard chooseCard(List<PowerUpCard> cards) {
        return null;
    }

    @Override
    public ActionType chooseAction(List<ActionType> actionTypeList) {
        return null;
    }
}
