package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.map.Map;

public interface PlayerSelector {
    boolean isSelectable(Map map, Player player);
}
