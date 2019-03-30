package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.map.Map;

import java.util.List;

public class BlackListFilterPlayerSelector implements PlayerSelector {

    List<Player> excluded;

    public BlackListFilterPlayerSelector(List<Player> excluded, PlayerSelector innerSelector){
        //TODO
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        //TODO
        return false;
    }
}
