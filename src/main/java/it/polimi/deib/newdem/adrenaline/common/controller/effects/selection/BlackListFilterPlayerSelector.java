package it.polimi.deib.newdem.adrenaline.common.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.map.Map;

import java.util.List;

public class BlackListFilterPlayerSelector implements PlayerSelector {

    private List<Player> excluded;
    private PlayerSelector innerSelector;

    public BlackListFilterPlayerSelector(List<Player> excluded, PlayerSelector innerSelector){
        this.excluded = excluded;
        this.innerSelector = innerSelector;
        //TODO
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        //TODO
        return false;
    }
}
