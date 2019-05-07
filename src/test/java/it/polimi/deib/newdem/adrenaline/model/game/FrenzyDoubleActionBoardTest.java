package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType.*;
import static org.junit.Assert.*;

public class FrenzyDoubleActionBoardTest {

    private FrenzyDoubleActionBoard ab;
    private ActionType t1;
    private ActionType t2;
    private ActionType t3;


    @Before
    public void setUp() {
        ab = new FrenzyDoubleActionBoard();
        t1 = new ActionType(MOVE1, RELOAD, SHOOT);
        t2 = new ActionType(MOVE4);
        t3 = new ActionType(MOVE2, GRAB);

    }

    @Test
    public void testGetBasicActions() {
        List<ActionFactory> factories = ab.getBasicActions();
        assertEquals(t1, factories.get(0).getType());
        assertEquals(t2, factories.get(1).getType());
        assertEquals(t3, factories.get(2).getType());
    }

    @Test
    public void testGetIterations() {
        assertEquals(2, ab.getIterations());
    }
}