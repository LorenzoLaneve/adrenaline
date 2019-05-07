package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

public class NegatedPlayerSelector implements PlayerSelector {

    private PlayerSelector innerSelector;

    public NegatedPlayerSelector(PlayerSelector innerSelector){
        this.innerSelector = innerSelector;
        //TODO
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        //TODO
        return false;
    }
}
