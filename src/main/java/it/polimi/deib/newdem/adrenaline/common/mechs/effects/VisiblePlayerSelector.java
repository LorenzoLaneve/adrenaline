package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.map.Map;

public class VisiblePlayerSelector implements PlayerSelector {

    Player sourcePlayer;

    public VisiblePlayerSelector(Player sourcePlayer){
        //TODO
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        //TODO
        return false;
    }
}
