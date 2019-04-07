package it.polimi.deib.newdem.adrenaline.common.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.map.Map;

public class VisiblePlayerSelector implements PlayerSelector {

    private Player sourcePlayer;

    public VisiblePlayerSelector(Player sourcePlayer){
        //TODO
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        //TODO
        return false;
    }
}
