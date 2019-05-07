package it.polimi.deib.newdem.adrenaline.controller.actions;

import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType.*;
import static org.junit.Assert.*;

public class ActionTypeTest {

    @Test
    public void testHashCode() {
        assertEquals(7, (new ActionType(MOVE1)).hashCode());
    }

    @Test
    public void testEqualsPositive() {
        assertEquals((new ActionType(MOVE1)), (new ActionType(MOVE1)));
    }

    @Test
    public void testEqualsNegative() {
        assertNotEquals((new ActionType(MOVE1)), (new ActionType(SHOOT)));
        assertNotEquals((new ActionType(MOVE1)), new Object());
        assertNotEquals((new ActionType(MOVE1)), null);
    }
}