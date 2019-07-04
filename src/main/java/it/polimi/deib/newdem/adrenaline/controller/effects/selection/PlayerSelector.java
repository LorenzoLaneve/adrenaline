package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Map;

/**
 * The player selector is a logical predicate that can discriminate players by different criteria.
 * Usually criteria include information about players relative locations.
 */
@FunctionalInterface
public interface PlayerSelector {
    /**
     * Method used to check whether the player meets the condition of the selector.
     * @param map context of the selector.
     * @param player the Player object that should be checked by this selector.
     * @return whether the player meets the condition of the selector.
     */
    boolean isSelectable(Map map, Player player);
}
