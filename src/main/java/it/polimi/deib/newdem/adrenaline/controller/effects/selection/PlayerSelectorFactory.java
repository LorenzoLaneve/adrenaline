package it.polimi.deib.newdem.adrenaline.controller.effects.selection;


import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.model.game.Player;

import java.util.List;

public interface PlayerSelectorFactory {

    PlayerSelector makeSelector(EffectVisitor visitor, List<Player> excluded );

}
