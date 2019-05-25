package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.MockMap;
import it.polimi.deib.newdem.adrenaline.model.map.OrdinaryTile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.polimi.deib.newdem.adrenaline.model.game.GameImpl.MAX_PLAYERS_PER_GAME;
import static org.junit.Assert.*;

public class GameImplTest {

    GameImpl game;
    GameParameters gp;
    Map map;
    Player p1;
    List<ColorUserPair> colorUserPairs;

    @Before
    public void setUp() throws Exception {
        gp = new GameParameters();
        p1 = new MockPlayer(PlayerColor.MAGENTA);
        map = Map.createMap(getClass().getClassLoader().getResource("JsonData.json").getFile());

        colorUserPairs = new ArrayList<>(MAX_PLAYERS_PER_GAME);
        colorUserPairs.add(new ColorUserPair(p1.getColor(), new User()));

        gp.setGameMap(map);
        gp.setColorUserOrder(colorUserPairs);

        game = new GameImpl(gp);
        game.init();
    }

    @Test
    public void testGetMap() throws Exception {
        assertNotNull(game.getMap());
        assertNotNull(game.getMap().getTile(new TilePosition(0,0)));
    }

    @Test
    public void testGetPlayer() throws Exception {
        GameParameters gp = new GameParameters();
        List<ColorUserPair> colorUserPairs = new ArrayList<>();

        colorUserPairs.add(new ColorUserPair(PlayerColor.MAGENTA, new User()));
        gp.setColorUserOrder(colorUserPairs);

        Game thisGame = new GameImpl(gp);
        ((GameImpl) thisGame).init();

        assertNotNull(thisGame.getPlayerFromColor(PlayerColor.MAGENTA));

        assertNotNull(game.getPlayerFromColor(PlayerColor.MAGENTA));

        assertNotEquals(thisGame.getPlayerFromColor(PlayerColor.MAGENTA),
                game.getPlayerFromColor(PlayerColor.MAGENTA));
    }

    @Test
    public void testIsInFrenzy() throws Exception {
        assertFalse(game.isInFrenzy());
    }

    @Test
    public void testShouldGoFrenzy() throws Exception {
        // Player p2 = new PlayerImpl(PlayerColor.GREEN, game, "Larry");
        // java.util.Map<PlayerColor, Player> MyMap = gp.getColorUserOrder();
        List<ColorUserPair> colorUserPairs = gp.getColorUserOrder();
        colorUserPairs.add(new ColorUserPair(PlayerColor.GREEN, new User()));
        GameParameters gameParameters = new GameParameters();

        gameParameters.setColorUserOrder(colorUserPairs);
        gameParameters.setGameMap(map);

        map.movePlayer(p2, map.getTile(new TilePosition(0,0)));

        game = new GameImpl(gameParameters);
        game.init();  //<<<<<

        Player p2 = game.getPlayerFromColor(PlayerColor.GREEN);
        // p2.init();

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
        p2.init();
        Player p3 = game.getPlayerFromColor(PlayerColor.MAGENTA);
        p3.takeMark(2, p2);
        game.goFrenzy();
        assertEquals(2, p3.getMarksFromPlayer(p2));
    }

    @Test
    public void testGoFrenzyFirstPlayerCard() throws Exception {

        List<ColorUserPair> colorUserPairs = gp.getColorUserOrder();
        colorUserPairs.add(new ColorUserPair(PlayerColor.GREEN, new User()));
        colorUserPairs.add(new ColorUserPair(PlayerColor.YELLOW, new User()));
        colorUserPairs.add(new ColorUserPair(PlayerColor.GRAY, new User()));
        gp.setColorUserOrder(colorUserPairs);

        GameImpl myGame = new GameImpl(gp);
        myGame.init();

        Player p2 = myGame.getPlayerFromColor(PlayerColor.GREEN);
        p2.assignFirstPlayerCard();

        assertEquals(3, myGame.getPlayerFromColor(PlayerColor.YELLOW).getMoves().size());
        assertEquals(3, myGame.getPlayerFromColor(PlayerColor.YELLOW).getMoves().size());
        assertEquals(3, myGame.getPlayerFromColor(PlayerColor.YELLOW).getMoves().size());

        myGame.goFrenzy();

        assertEquals(3, myGame.getPlayerFromColor(PlayerColor.GREEN).getMoves().size());
        assertEquals(2, myGame.getPlayerFromColor(PlayerColor.YELLOW).getMoves().size());
        assertEquals(2, myGame.getPlayerFromColor(PlayerColor.GRAY).getMoves().size());

    }

    @Test
    public void testSetUserForColor() throws Exception {
        game.setUserForColor(new User(), PlayerColor.MAGENTA);
    }

    @Test
    public void testGetUserByPlayer() throws Exception {
        Player p1 = game.getPlayerFromColor(PlayerColor.MAGENTA);
        assertNotNull(game.getUserByPlayer(p1));
    }

    @Test(expected = IllegalStateException.class)
    public void testGoFrenzyNegative() throws Exception {
        game.goFrenzy();
        assertFalse(game.shouldGoFrenzy());
        game.goFrenzy();
    }

    @Test
    public void testGetPlayerFromColorNegative() throws Exception {
        GameParameters gp = new GameParameters();
        gp.setColorUserOrder(Arrays.asList(new ColorUserPair(
                PlayerColor.GREEN, new User()
        )));
        Game myGame = new GameImpl(gp);
        myGame.init();
        assertNull(myGame.getPlayerFromColor(PlayerColor.MAGENTA));
    }

    @Test(expected = IllegalStateException.class)
    public void testInitNegativeNoPlayers() throws Exception {
        GameParameters gp = new GameParameters();
        Game mygame = new GameImpl(gp);
        mygame.init();
    }

    @Test(expected = IllegalStateException.class)
    public void testInitNegativeDouble() throws Exception {
        GameParameters gp = new GameParameters();
        gp.setColorUserOrder(Arrays.asList(
                new ColorUserPair(PlayerColor.MAGENTA, new User())
        ));
        Game myGame = new GameImpl(gp);
        myGame.init();
        try{
            myGame.init();
        }
        catch (IllegalStateException e) {
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetUserForColorNegativeNoColor() throws Exception {
        game.setUserForColor(new User(), PlayerColor.GREEN);
    }

    @Test(expected = IllegalStateException.class)
    public void testInitNEgativeNoPlayers() throws Exception {
        GameParameters gp = new GameParameters();
        Game myGame = new GameImpl(gp);
        myGame.init();
    }

    @Test(expected = IllegalStateException.class)
    public void testTesetNegativeNoPlayers() throws Exception {
        GameParameters gp = new GameParameters();
        Game myGame = new GameImpl(gp);
        ((GameImpl) myGame).reset();
    }
}