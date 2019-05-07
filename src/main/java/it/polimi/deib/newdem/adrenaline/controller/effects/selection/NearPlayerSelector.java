package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

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
