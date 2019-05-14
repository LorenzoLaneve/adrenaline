package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameListener;
import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.GameView;
import it.polimi.deib.newdem.adrenaline.view.MapView;
import it.polimi.deib.newdem.adrenaline.view.PlayerView;

public class VirtualGameView implements GameView, GameListener {


    @Override
    public User getUserForColor(PlayerColor color) {
        return null;
    }

    @Override
    public void addPlayerView(PlayerView pv) {
        // TODO
    }

    @Override
    public void addMapView(MapView mv) {
        // TODO
    }

    @Override
    public void gameWillStart(Game game) {
        // TODO
    }

    @Override
    public void gameWillEnd(Game game) {
        // TODO
    }

}
