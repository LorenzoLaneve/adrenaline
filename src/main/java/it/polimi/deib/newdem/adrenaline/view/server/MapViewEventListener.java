package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public interface MapViewEventListener {

    void userDidSelectTile(User user, TilePosition tile);

    void userDidSelectPlayer(User user, PlayerColor player);

}
