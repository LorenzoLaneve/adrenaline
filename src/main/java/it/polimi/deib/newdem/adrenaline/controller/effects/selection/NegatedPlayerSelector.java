package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

public class NegatedPlayerSelector implements PlayerSelector {

    private PlayerSelector innerSelector;

    public NegatedPlayerSelector(PlayerSelector innerSelector){
        this.innerSelector = innerSelector;
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        return !innerSelector.isSelectable(map, player);
    }
}
