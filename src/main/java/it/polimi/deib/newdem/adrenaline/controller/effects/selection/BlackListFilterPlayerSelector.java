package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

import java.util.List;

public class BlackListFilterPlayerSelector implements PlayerSelector {

    private List<Player> excluded;
    private PlayerSelector innerSelector;

    public BlackListFilterPlayerSelector(List<Player> excluded, PlayerSelector innerSelector){
        this.excluded = excluded;
        this.innerSelector = innerSelector;
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        return !excluded.contains(player) && innerSelector.isSelectable(map, player);
    }
}
