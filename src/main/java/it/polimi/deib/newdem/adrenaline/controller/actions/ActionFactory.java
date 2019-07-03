package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;

public interface ActionFactory {

    Action makeAction(Player actor, TurnDataSource dataSource);

    ActionType getType();
}
