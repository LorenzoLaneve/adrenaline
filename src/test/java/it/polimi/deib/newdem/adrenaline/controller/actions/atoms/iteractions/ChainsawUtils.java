package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;


import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

/**
 * Helper class for testing damages
 *
 * Assists in finding and using the chainsaw
 */
public final class ChainsawUtils {

    /**
     * ID of the weapon chainsaw
     */
    public static final int CHAINSAW_ID = 22;

    /**
     * Finds the tile the chainsaw currently belongs to
     * @param map where to look for the chainsaw
     * @return tile the chainsaw belongs to
     * @throws IllegalStateException if the chainsaw is not on the given map
     */
    public static Tile whereIsChainsaw(Map map) {
        for(AmmoColor color : AmmoColor.values()) {
            Tile spawn = map.getSpawnPointFromColor(color);
            for (WeaponCard wc : spawn.inspectWeaponSet().getWeapons()) {
                if (wc.getCardID() == CHAINSAW_ID) {
                    return spawn;
                }
            }
        }
        throw new IllegalStateException();
    }

}
