package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

public interface MapListener {

    void playerDidMove(Player player, Tile source, Tile destination);

    // TODO methods definition for other map events.


}
