package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

public class IntersectPlayerSelector implements PlayerSelector {

    private PlayerSelector firstOp;
    private PlayerSelector secondOp;

    public IntersectPlayerSelector(PlayerSelector firstOp, PlayerSelector secondOp){
        this.firstOp = firstOp;
        this.secondOp = secondOp;
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        return firstOp.isSelectable(map, player) && secondOp.isSelectable(map, player);
    }
}
