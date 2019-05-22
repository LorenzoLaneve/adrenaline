package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

public class NearPlayerSelector implements PlayerSelector {

    private Player sourcePlayer;
    private int minDistance;
    private int maxDistance;

    public NearPlayerSelector(Player sourcePlayer, int minDistance, int maxDistance){
        this.sourcePlayer = sourcePlayer;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        int distance = sourcePlayer.getTile().distanceFrom(player.getTile());

        return minDistance <= distance && distance <= maxDistance;
    }
}
