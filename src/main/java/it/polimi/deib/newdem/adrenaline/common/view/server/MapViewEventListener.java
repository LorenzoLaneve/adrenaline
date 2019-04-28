package it.polimi.deib.newdem.adrenaline.common.view.server;

import it.polimi.deib.newdem.adrenaline.common.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.common.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;

public interface MapViewEventListener {

    void userDidSelectTile(User user, TilePosition tile);

    void userDidSelectPlayer(User user, PlayerColor player);

}
