package it.polimi.deib.newdem.adrenaline.common.controller.effects;


import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

import java.util.List;

public interface PlayerSelectorFactory {

    PlayerSelector makeSelector(EffectVisitor visitor, List<Player> excluded );

}
