package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.rigged.GameRigged;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.GRAY;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.GREEN;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.MAGENTA;

public class KillTrackIntegrationTest {

    GameRigged game;

    @Before
    public void setUp() {
        game = TestingUtils.makeRiggedGame(MAGENTA, GRAY, GREEN);
    }

    @Test
    public void testTurnWithDeadPlayer() {

        Player p1 = game.getPlayerFromColor(MAGENTA);
        Player p2 = game.getPlayerFromColor(GRAY);
        Player p3 = game.getPlayerFromColor(GREEN);


    }
}
