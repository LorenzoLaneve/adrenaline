package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.map.Map;

public class NearPlayerSelector implements PlayerSelector {

    private int minDistance;
    private int maxDistance;

    public NearPlayerSelector(Player sourcePlayer, int minDistance, int maxDistance){
        //TODO
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        //TODO
        return false;
    }
}
