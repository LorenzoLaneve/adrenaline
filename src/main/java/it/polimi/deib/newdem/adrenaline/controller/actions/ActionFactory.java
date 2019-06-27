package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

// TODO cross reference
public interface ActionFactory {

    Action makeAction(Player actor, ActionDataSource actionDataSource);

    ActionType getType();
}
