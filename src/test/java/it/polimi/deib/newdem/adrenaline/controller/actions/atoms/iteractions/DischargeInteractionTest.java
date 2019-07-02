package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;


public class DischargeInteractionTest {

    Game game;
    Player m;
    Player g;

    @Before
    public void setUp() throws Exception {
        TestingUtils.loadSingleton();
        game = TestingUtils.makeTestGame(PlayerColor.MAGENTA, PlayerColor.GREEN);
        m = game.getPlayerFromColor(PlayerColor.MAGENTA);
        g = game.getPlayerFromColor(PlayerColor.GREEN);
    }

    @Test
    public void testExecute() throws Exception {
    }
}