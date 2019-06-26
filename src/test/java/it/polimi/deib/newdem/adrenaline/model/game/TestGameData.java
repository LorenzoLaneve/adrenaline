package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

public class TestGameData {

    GameData gameData;

    @Before
    public void setUp() throws Exception {
        gameData = new GameData();
    }

    @Test
    public void addPlayer() {
        gameData.addPlayer("test", PlayerColor.GRAY, true);
    }

}