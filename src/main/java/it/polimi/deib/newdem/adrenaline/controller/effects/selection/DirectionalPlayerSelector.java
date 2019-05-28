package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Direction;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

public class DirectionalPlayerSelector implements PlayerSelector {

    private Player sourcePlayer;
    private Direction direction;

    public DirectionalPlayerSelector(Player sourcePlayer, Direction direction) {
        this.sourcePlayer = sourcePlayer;
        this.direction = direction;
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        // TODO
        return false;
    }
}
