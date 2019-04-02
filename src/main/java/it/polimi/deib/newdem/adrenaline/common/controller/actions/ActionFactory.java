package it.polimi.deib.newdem.adrenaline.common.controller.actions;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

// TODO cross reference
public interface ActionFactory {

    Action makeAction(Player actor);

}
