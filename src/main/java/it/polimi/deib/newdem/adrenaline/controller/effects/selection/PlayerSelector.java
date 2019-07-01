package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

public interface PlayerSelector {
    /**
     * Method used to check whether the player meets the condition of the selector.
     * @param map context of the selector.
     * @param player to check
     * @return whether the player meets the condition of the selector.
     */
    boolean isSelectable(Map map, Player player);
}
