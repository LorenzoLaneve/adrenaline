package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

import java.util.ArrayList;
import java.util.List;

public class WhileListFilterPlayerSelector implements PlayerSelector {

    private List<Player> allowed;

    public WhileListFilterPlayerSelector(List<Player> allowed){
        this.allowed = new ArrayList<>(allowed);
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        return allowed.contains(player);
    }
}
