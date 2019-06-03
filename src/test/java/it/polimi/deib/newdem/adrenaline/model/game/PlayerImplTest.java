package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.action_board.ActionBoardImpl;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerImpl;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerNotInitializedException;
import it.polimi.deib.newdem.adrenaline.model.items.OutOfSlotsException;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.MapBuilder;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Before;
import org.junit.Test;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType.*;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.*;
import static org.junit.Assert.*;

public class PlayerImplTest {

    private Player p;
    private Player q;
    private Game g;
    private ActionType t1;
    private ActionType t2;

    @Before
    public void setUp() throws Exception {
        GameParameters gp = new GameParameters();
        // p1 = new PlayerImpl(PlayerColor.MAGENTA);
        String encodedPath = getClass().getClassLoader().getResource("JsonData.json").getFile();
        String decodedPath = URLDecoder.decode(encodedPath, StandardCharsets.UTF_8.name());
        gp.setGameMap(Map.createMap(decodedPath));
        User user1 = new User();
        user1.setName("Larry");
        User user2 = new User();
        user2.setName("Carl");
        gp.setColorUserOrder(Arrays.asList(
                new ColorUserPair(YELLOW, user1),
                new ColorUserPair(GRAY, user2)
        ));
        g = new GameImpl(gp);
        g.init();
        //p = new PlayerImpl(YELLOW, g,"Larry");
        //p.init();
        p = g.getPlayerFromColor(YELLOW);
        //q = new PlayerImpl(GRAY, g, "Carl"); // q is not initialized
        q = g.getPlayerFromColor(GRAY);
        t2 = new ActionType(MOVE1, SHOOT);
        t1 = new ActionType(MOVE2, GRAB);
    }

    @Test
    public void testGetGame() {
        assertEquals(g, p.getGame());
    }

    @Test
    public void testGetInventory() {
        assertNotNull(p.getInventory());
    }

    @Test
    public void testGetName() {
        assertEquals("Larry", p.getName());
    }

    @Test
    public void testGetColor() {
        assertEquals(YELLOW, p.getColor());
    }

    @Test
    public void testGetMoves() {
        Player p2 = new MockPlayer(GREEN);
        assertNotNull(p);
        assertNotNull(p.getMoves());
        assertTrue(p.getMoves().containsAll(
                (new ActionBoardImpl()).getBasicActions()
        ));
        assertFalse(p.getMoves().contains(
                new ConcreteActionFactory(t1)
        ));
        assertFalse(p.getMoves().contains(
                new ConcreteActionFactory(t2)
        ));

        try {
            p.getInventory().addPowerUp(new MockPowerUpCard());
        }
        catch (OutOfSlotsException e) {fail();}
        // take some damage, test for updated moves
        OrdinaryDamageBoard d = new OrdinaryDamageBoard(p);
        p.registerDamageBoard(d);
        d.takeDamage(3, p2);


        assertTrue(p.getMoves().containsAll(
                (new ActionBoardImpl()).getBasicActions()
        ));
        assertTrue(p.getMoves().contains(
                new ConcreteActionFactory(t1)
        ));
        assertTrue(p.getMoves().contains(
                new ConcreteActionFactory(USE_POWERUP)
        ));

        d.takeDamage(3, p2);

        assertTrue(p.getMoves().containsAll(
                (new ActionBoardImpl()).getBasicActions()
        ));
        assertTrue(p.getMoves().contains(
                new ConcreteActionFactory(t1)
        ));
        assertTrue(p.getMoves().contains(
                new ConcreteActionFactory(t2)
        ));
    }

    @Test
    public void testGetDeaths() {
        assertEquals(0, p.getDeaths());
    }

    @Test
    public void testGetTotalDamage() {
        // how do I take damage at all?
        assertEquals(0,p.getTotalDamage());
        OrdinaryDamageBoard d = new OrdinaryDamageBoard(p);
        p.registerDamageBoard(d);
        d.takeDamage(5,new MockPlayer());
        assertEquals(5, p.getTotalDamage());
    }

    @Test
    public void testGetDamager() {
        Player p2 = new MockPlayer();
        OrdinaryDamageBoard d = new OrdinaryDamageBoard(p);
        p.registerDamageBoard(d);
        d.takeDamage(2,p2);
        assertEquals(p2, p.getDamager(0));
    }

    @Test
    public void testGetDamageFromPlayer() {
        Player p2 = new MockPlayer();
        OrdinaryDamageBoard d = new OrdinaryDamageBoard(p);
        p.registerDamageBoard(d);
        d.takeDamage(2,p2);
        assertEquals(2, p.getDamageFromPlayer(p2));
    }

    @Test
    public void testGetMarksFromPlayer() {
        Player p2 = new MockPlayer();
        OrdinaryDamageBoard d = new OrdinaryDamageBoard(p);
        p.registerDamageBoard(d);
        d.takeMark(2,p2);
        assertEquals(2, p.getMarksFromPlayer(p2));
    }

    @Test
    public void testIsDead() {
        assertFalse(p.isDead());
    }

    @Test
    public void testRegisterDamageBoard() {
        p.registerDamageBoard(new OrdinaryDamageBoard(p));
    }

    @Test
    public void testTakeDamage() {
        p.takeDamage(1, new MockPlayer());
    }

    @Test
    public void testTakeMark() {
        p.takeMark(1, new MockPlayer(MAGENTA));
    }

    //*
    //*NEGATIVES
    //*

    @Test
    public void testGetGameNegative() {
        q.getGame();
        // this should not throw an exception
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetInventoryNegative() {
        q = new PlayerImpl(YELLOW, new MockGame(), "Dummy");
        q.getInventory();
    }

    @Test
    public void testGetNameNegative() {
        q.getName(); // this should not throw an exception
    }

    @Test
    public void testGetColorNegative() {
        assertEquals(YELLOW, p.getColor());
        // this should not throw an exception
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetMovesNegative() {
        q = new PlayerImpl(YELLOW, new MockGame(), "Dummy");
        q.getMoves();
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetDeathsNegative() {
        q = new PlayerImpl(YELLOW, new MockGame(), "Dummy");
        q.getDeaths();
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetTotalDamageNegative() {
        q = new PlayerImpl(YELLOW, new MockGame(), "Dummy");
        q.getTotalDamage();
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetDamagerNegative() {
        q = new PlayerImpl(YELLOW, new MockGame(), "Dummy");
        q.getDamager(2);
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetDamageFromPlayerNegative() {
        q = new PlayerImpl(YELLOW, new MockGame(), "Dummy");
        q.getDamageFromPlayer(p);
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetMarksFromPlayerNegative() {
        q = new PlayerImpl(YELLOW, new MockGame(), "Dummy");
        q.getMarksFromPlayer(p);
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testIsDeadNegative() {
        q = new PlayerImpl(YELLOW, new MockGame(), "Dummy");
        q.isDead();
    }

    @Test
    public void testRegisterDamageBoard2() {
        q.registerDamageBoard(new OrdinaryDamageBoard(q));
        // this should not throw exception even with uninitialized player
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeDamageNegativeNull() {
        p.takeDamage(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeDamageNegativeAmount() {
        p.takeDamage(-1, new MockPlayer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeDamageNegativThis() {
        p.takeDamage(1, p);
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testTakeDamageNegativeInit() {
        q = new PlayerImpl(YELLOW, new MockGame(), "Dummy");
        q.takeDamage(1, new MockPlayer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeMarkNegativeNull() {
        p.takeMark(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeMarkNegativeAmount() {
        p.takeMark(-1, new MockPlayer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeMarkNegativeThis() {
        p.takeMark(1, p);
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testTakMarkNegativeInit() {
        q = new PlayerImpl(YELLOW, new MockGame(), "Dummy");
        q.takeMark(1, new MockPlayer());
    }
}