
package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.items.Deck;
import it.polimi.deib.newdem.adrenaline.model.items.DeckAlreadyLoadedException;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponDeck;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class AdrenalineGameControllerTest {

    AdrenalineGameController agc;
    User u1;
    User u2;
    User u3;

    @Before
    public void setUp() throws Exception {
        try {
            WeaponDeck.loadCardsFromJson(GameImpl.WEAPON_DECK_PATH);
        }
        catch (DeckAlreadyLoadedException e) {
            // ok, do not abort
        }
        Config config = Config.getDefaultConfig(); // config

        agc = new AdrenalineGameController(new LobbyControllerImpl(null, config));
        u1 = new User();
        u2 = new User();
        u3 = new User();
        doSetUpGame();

    }

    @Test
    public void testSetupGame() throws Exception {
        GameController gameController =
                new AdrenalineGameController(new LobbyControllerImpl(null, Config.getDefaultConfig()));

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
        u1.setName("Carl");
        u2.setName("Larry");
        u3.setName("Steve");

        agc.setupGame(Arrays.asList(
                u1,u2, u3
        ));
    }

    @Test
    public void testSctipted() throws Exception {
        AdrenalineGameController agc =
                new ScriptedAdrenalineGameController(new LobbyControllerImpl(null, Config.getDefaultConfig()));
        ((ScriptedAdrenalineGameController) agc).injectTurnScripts(
                new ScriptedDataSource(null, new ActionType(AtomicActionType.MOVE1))
        );

        agc.setupGame(Arrays.asList(new User(), new User(), new User()));
        agc.runGame();
    }

    @Test
    public void testUserDisconnect() throws Exception {
        doSetUpGame();
        agc.userDidDisconnect(u1);
        agc.userDidReconnect(u1);
    }
}