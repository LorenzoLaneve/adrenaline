package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.controller.GameControllerFactory;
import it.polimi.deib.newdem.adrenaline.controller.effects.TestMachineGunEffect;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerData;
import it.polimi.deib.newdem.adrenaline.model.items.DeckAlreadyLoadedException;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponDeck;
import it.polimi.deib.newdem.adrenaline.model.map.TestingMapBuilder;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GameDataTest {

    private GameParameters gp;
    private Game game;
    private GameData gameData;
    private GameData uninitializedGameData;
    private User carl;

    @Before
    public void setUp() throws Exception {
        TestingUtils.loadSingleton();
        SneakyGameListener listener = new SneakyGameListener();
        gp = GameParameters.fromConfig(Config.getDefaultConfig());
        gp.setGameMap(TestingMapBuilder.getNewMap(this.getClass()));
        gp.setMinPlayers(1);
        carl = new User();
        carl.setName("Carl");
        gp.setColorUserOrder(Arrays.asList(
                new ColorUserPair(PlayerColor.MAGENTA, carl)
        ));
        Game game = new GameImpl(gp);
        game.setGameListener(listener);
        game.init();

        gameData = listener.getGameData();
        uninitializedGameData = new GameData();
    }

    @Test
    public void testFinalized() throws Exception {
        try {
            gameData.setFinalized();
        }
        catch (IllegalStateException e) { /* ok */ }
        assertFalse(uninitializedGameData.isFinalized());
        assertTrue(gameData.isFinalized());
    }

    @Test
    public void testGetPlayers() throws Exception {
        try{
            uninitializedGameData.getPlayers();
        }
        catch (IllegalStateException e) { /* ok */ }

        assertFalse(gameData.getPlayers().isEmpty());
        GameData.UserColorPair ucp = gameData.getPlayers().get(0);
        assertEquals(ucp.getUsername(), carl.getName());
        assertEquals(PlayerColor.MAGENTA, ucp.getColor());
        assertFalse(ucp.isOnline());
    }
}