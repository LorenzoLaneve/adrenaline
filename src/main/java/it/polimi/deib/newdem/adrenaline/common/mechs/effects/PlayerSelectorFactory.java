package it.polimi.deib.newdem.adrenaline.common.mechs.effects;


import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;

import java.util.List;

public interface PlayerSelectorFactory {

    PlayerSelector makeSelector(EffectVisitor visitor, List<Player> excluded );

}
