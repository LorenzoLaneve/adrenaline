package it.polimi.deib.newdem.adrenaline.common.mechs.effects;


import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.actions.Action;

import java.util.List;

public interface PlayerSelectorFactory {

    PlayerSelector makeSelector(Action action, List<Player> excluded );

}
