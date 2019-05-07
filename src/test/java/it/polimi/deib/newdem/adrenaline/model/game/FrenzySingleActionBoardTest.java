package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType.*;
import static org.junit.Assert.*;

public class FrenzySingleActionBoardTest {

    private FrenzySingleActionBoard ab;
    private ActionType t1;
    private ActionType t2;

    @Before
    public void setUp() {
        ab = new FrenzySingleActionBoard();
        t1 = new ActionType(MOVE2, RELOAD, SHOOT);
        t2 = new ActionType(MOVE3, GRAB);
    }

    @Test
    public void testGetBasicActions() {
        assertEquals(t1, ab.getBasicActions().get(0).getType());
        assertEquals(t2, ab.getBasicActions().get(1).getType());

    }

    @Test
    public void testGetIterations() {
        assertEquals(1, ab.getIterations());
    }
}