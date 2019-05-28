package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

public class AnyPlayerSelector implements PlayerSelector {

    public AnyPlayerSelector() {
        // nothing here
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        return true;
    }
}
