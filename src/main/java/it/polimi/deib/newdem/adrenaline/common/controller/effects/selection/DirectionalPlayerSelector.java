package it.polimi.deib.newdem.adrenaline.common.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.map.Direction;
import it.polimi.deib.newdem.adrenaline.common.model.map.Map;

public class DirectionalPlayerSelector implements PlayerSelector {

    private MetaPlayer sourcePlayer;
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
