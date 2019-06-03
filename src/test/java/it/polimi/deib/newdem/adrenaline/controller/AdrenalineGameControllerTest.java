
package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.model.game.MockPowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AdrenalineGameControllerTest {

    AdrenalineGameController agc;

    @Before
    public void setUp(){
        Config config = Config.getDefaultConfig(); // config
        agc = new AdrenalineGameController(new LobbyControllerImpl(Config.getDefaultConfig()));
        doSetUpGame();
    }

    @Test
    public void testSetupGame() throws Exception {
        GameController gameController =
                new AdrenalineGameController(new LobbyControllerImpl(Config.getDefaultConfig()));

        gameController.setupGame(Arrays.asList(
                new User(), new User(), new User()
        ));
    }

    @Test
    public void testRunGame() throws Exception {
        int i = 1;
        agc.runGame();
    }

    private void doSetUpGame(){

        User u1 = new User();
        User u2 = new User();
        u1.setName("Carl");
        u2.setName("Larry");

        agc.setupGame(Arrays.asList(
                u1,u2
        ));
    }

    @Test
    public void testSctipted() throws Exception {
        AdrenalineGameController agc =
                new ScriptedAdrenalineGameController(new LobbyControllerImpl(Config.getDefaultConfig()));
        ((ScriptedAdrenalineGameController) agc).injectTurnScripts(
                new ScriptedDataSource(new ActionType(AtomicActionType.MOVE1))
        );

        agc.setupGame(Arrays.asList(new User(), new User()));
        agc.runGame();
    }
}