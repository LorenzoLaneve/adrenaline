package it.polimi.deib.newdem.adrenaline.controller.actions;

import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.*;
import static org.junit.Assert.*;

/**
 * Tests for {@code ActionType}
 *
 * @see it.polimi.deib.newdem.adrenaline.controller.actions.ActionType
 */
public class ActionTypeTest {

    /**
     * Tests the hash code functionality
     */
    @Test
    public void testHashCode() {
        assertEquals(7, (new ActionType(MOVE1)).hashCode());
    }

    /**
     * Tests the equals functionality
     */
    @Test
    public void testEqualsPositive() {
        assertEquals((new ActionType(MOVE1)), (new ActionType(MOVE1)));
    }

    /**
     * Tests a fail case for the equals functionality
     */
    @Test
    public void testEqualsNegative() {
        assertNotEquals((new ActionType(MOVE1)), (new ActionType(SHOOT)));
        assertNotEquals((new ActionType(MOVE1)), new Object());
        assertNotEquals((new ActionType(MOVE1)), null);
    }
}