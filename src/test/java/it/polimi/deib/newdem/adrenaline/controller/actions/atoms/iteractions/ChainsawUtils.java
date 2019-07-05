package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public final class ChainsawUtils {

    public static final int CHAINSAW_ID = 22;

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
