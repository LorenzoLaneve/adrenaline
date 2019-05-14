package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public interface GameView {

    /**
     * Returns the user associated with this color for the game they are in.
     */
    User getUserForColor(PlayerColor color);

    /**
     *
     */
    void addPlayerView(PlayerView pv);

    /**
     *
     */
    void addMapView(MapView mv);

}
