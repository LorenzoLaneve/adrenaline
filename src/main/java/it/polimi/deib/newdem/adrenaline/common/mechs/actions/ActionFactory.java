package it.polimi.deib.newdem.adrenaline.common.mechs.actions;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;

// TODO cross reference
public interface ActionFactory {

    Action makeAction(Player actor);

}
