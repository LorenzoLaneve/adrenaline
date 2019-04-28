package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionType;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.common.controller.actions.AtomicActionType.*;
import static org.junit.Assert.*;

public class OrdinaryActionBoardTest {

    private OrdinaryActionBoard ab;
    private ActionType t1;
    private ActionType t2;
    private ActionType t3;

    @Before
    public void setUp() {
        ab = new OrdinaryActionBoard();
        t1 = new ActionType(MOVE3);
        t2 = new ActionType(MOVE1, GRAB);
        t3 = new ActionType(SHOOT);
    }

    @Test
    public void testGetBasicActions() {
        assertEquals(t1, ab.getBasicActions().get(0).getType());
        assertEquals(t2, ab.getBasicActions().get(1).getType());
        assertEquals(t3, ab.getBasicActions().get(2).getType());
    }

    @Test
    public void testGetIterations() {
        assertEquals(2, ab.getIterations());
    }
}