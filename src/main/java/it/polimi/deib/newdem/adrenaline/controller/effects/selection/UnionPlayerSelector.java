package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

public class UnionPlayerSelector implements PlayerSelector {

    private PlayerSelector firstOp;
    private PlayerSelector secondOp;

    /**
     * Selects all the players that are selected by one of the given selectors.
     * @see PlayerSelector for further information
     */
    public UnionPlayerSelector(PlayerSelector firstOp, PlayerSelector secondOp){
        this.firstOp = firstOp;
        this.secondOp = secondOp;
    }

    @Override
    public boolean isSelectable(Map map, Player player) {
        return firstOp.isSelectable(map, player) || secondOp.isSelectable(map, player);
    }
}
