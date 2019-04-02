package it.polimi.deib.newdem.adrenaline.common.controller.effects;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.map.Map;

public class NegatedPlayerSelector implements PlayerSelector {

    public NegatedPlayerSelector(PlayerSelector innerSelector){
        //TODO
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        //TODO
        return false;
    }
}
