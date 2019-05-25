package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.MockMap;
import it.polimi.deib.newdem.adrenaline.model.map.OrdinaryTile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import org.junit.Before;
import org.junit.Test;

import javax.tools.JavaCompiler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;

import static org.junit.Assert.*;

public class GameImplTest {

    GameImpl game;
    GameParameters gp;
    Map map;
    Player p1;
    EnumMap<PlayerColor, Player> colorPlayerEnumMap;

    @Before
    public void setUp() throws Exception {
        gp = new GameParameters();
        p1 = new MockPlayer(PlayerColor.MAGENTA);
        map = Map.createMap(getClass().getClassLoader().getResource("JsonData.json").getFile());

        colorPlayerEnumMap = new EnumMap<>(PlayerColor.class);
        colorPlayerEnumMap.put(p1.getColor(), p1);

        gp.setMap(map);
        gp.setColorPlayerMap(colorPlayerEnumMap);

        game = new GameImpl(gp);
        game.reset();
    }

    @Test
    public void testGetMap() throws Exception {
        assertNotNull(game.getMap());
        assertNotNull(game.getMap().getTile(new TilePosition(0,0)));
    }

    @Test
    public void testGetPlayer() throws Exception {
        MockPlayer p1 = new MockPlayer(PlayerColor.MAGENTA);
        GameParameters gp = new GameParameters();
        EnumMap<PlayerColor, Player> colorPlayerEnumMap = new EnumMap<>(PlayerColor.class);

        colorPlayerEnumMap.put(p1.getColor(), p1);
        gp.setColorPlayerMap(colorPlayerEnumMap);

        Game thisGame = new GameImpl(gp);

        assertEquals(p1, thisGame.getPlayerFromColor(PlayerColor.MAGENTA));

        assertEquals(this.p1, game.getPlayerFromColor(PlayerColor.MAGENTA));
    }

    @Test
    public void testIsInFrenzy() throws Exception {
        assertFalse(game.isInFrenzy());
    }

    @Test
    public void testShouldGoFrenzy() throws Exception {
        Player p2 = new PlayerImpl(PlayerColor.GREEN, game, "Larry");
        java.util.Map<PlayerColor, Player> MyMap = gp.getColorPlayerMap();
        MyMap.put(PlayerColor.GREEN, p2);
        GameParameters gameParameters = new GameParameters();

        gameParameters.setColorPlayerMap(MyMap);
        gameParameters.setMap(map);

        map.movePlayer(p2, map.getTile(new TilePosition(0,0)));

        game = new GameImpl(gameParameters);
        game.reset();
        p2.init();

        assertFalse(game.shouldGoFrenzy());
        for(int i = 0; i <= GameParameters.KILLTRACK_STARTING_SIZE_DEFAULT; i++) {
            Turn t = game.getNextTurn();
            p2.takeDamage(10, p1);
            game.concludeTurn(t);
        }
        assertTrue(game.isInFrenzy());
    }

    @Test
    public void testReset() throws Exception {
        game.reset();
    }

    @Test
    public void testGetNextTurn() throws Exception {
        assertNotNull(game.getNextTurn());
        // no concludeTurn, which would add a new turn
        assertNull(game.getNextTurn());
    }

    @Test
    public void testConcludeTurn() throws Exception {
        Turn t = game.getNextTurn();
        game.concludeTurn(t);
        assertTrue(game.getNextTurn() instanceof OrdinaryTurn);
    }

    @Test
    public void testGoFrenzy() throws Exception {
        Player p2 = new MockPlayer();
        p1.takeMark(2, p2);
        game.goFrenzy();
        assertEquals(2, p1.getMarksFromPlayer(p2));
    }

    @Test
    public void testGoFrenzyFirstPlayerCard() throws Exception {
        Player p2 = new MockPlayer(PlayerColor.GREEN);
        Player p3 = new MockPlayer(PlayerColor.YELLOW);
        Player p4 = new MockPlayer(PlayerColor.GRAY);

        p2.assignFirstPlayerCard();

        java.util.Map<PlayerColor, Player> MyMap = gp.getColorPlayerMap();
        MyMap.put(p2.getColor(), p2);
        MyMap.put(p3.getColor(), p3);
        MyMap.put(p4.getColor(), p4);

        gp.setColorPlayerMap(MyMap);
        gp.setPlayerOrder(Arrays.asList(
                p1,
                p2,
                p3,
                p4
        ));

        GameImpl myGame = new GameImpl(gp);
        myGame.reset();

        p2.init();
        p3.init();

        myGame.goFrenzy();
    }

    @Test(expected = IllegalStateException.class)
    public void testgoFrenzyNegative() throws Exception {
        game.goFrenzy();
        assertFalse(game.shouldGoFrenzy());
        game.goFrenzy();
    }
}