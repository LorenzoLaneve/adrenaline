package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public class SneakyGameListener implements GameListener {

    GameData gameData;

    /**
     * Mock GameListener used for testing.
     */
    public GameData getGameData() {
        return gameData;
    }

    @Override
    public void gameDidInit(Game game, GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void gameWillEnd(Game game, GameResults gameResults) {

    }

    @Override
    public void userDidEnterGame(User user, Player player) {

    }

    @Override
    public void userDidExitGame(User user, Player player) {

    }

    @Override
    public void playerRestoredMatchData(Game game, Player player) {

    }
}
