package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.map.Map;

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
