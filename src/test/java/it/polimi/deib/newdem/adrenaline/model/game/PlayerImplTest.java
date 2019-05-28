package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.action_board.ActionBoardImpl;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerImpl;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerNotInitializedException;
import it.polimi.deib.newdem.adrenaline.model.items.OutOfSlotsException;
import org.junit.Before;
import org.junit.Test;

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
    public void setUp() {
        g = new MockGame();
        p = new PlayerImpl(YELLOW, g,"Larry");
        p.init();
        q = new PlayerImpl(GRAY, g, "Carl"); // q is not initialized
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
        q.getMoves();
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetDeathsNegative() {
        q.getDeaths();
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetTotalDamageNegative() {
        q.getTotalDamage();
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetDamagerNegative() {
        q.getDamager(2);
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetDamageFromPlayerNegative() {
        q.getDamageFromPlayer(p);
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testGetMarksFromPlayerNegative() {
        q.getMarksFromPlayer(p);
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testIsDeadNegative() {
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
        q.takeMark(1, new MockPlayer());
    }
}