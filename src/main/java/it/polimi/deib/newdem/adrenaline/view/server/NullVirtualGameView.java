package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.GameListener;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.GameView;

public class NullVirtualGameView extends VirtualGameView {
    @Override
    public void gameDidInit(Game game, GameData gameData) {

    }

    @Override
    public void gameWillEnd(Game game) {

    }

    @Override
    public void userDidEnterGame(User user, Player player) {

    }

    @Override
    public void userDidExitGame(User user, Player player) {

    }

    @Override
    public void setGameData(GameData data) {

    }

    @Override
    public void disablePlayer(PlayerColor color) {

    }

    @Override
    public void enablePlayer(PlayerColor color) {

    }
}
