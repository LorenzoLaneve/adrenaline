package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.map.Direction;
import it.polimi.deib.newdem.adrenaline.common.mechs.map.Map;

public class DirectionalPlayerSelector implements PlayerSelector {

    private MetaPlayer suorcePlayer;
    private Direction direction;

    public DirectionalPlayerSelector(Player sourcePlayer, Direction direction){
        //TODO
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        //TODO
        return false;
    }
}
