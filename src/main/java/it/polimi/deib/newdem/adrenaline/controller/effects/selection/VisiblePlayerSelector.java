package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

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
