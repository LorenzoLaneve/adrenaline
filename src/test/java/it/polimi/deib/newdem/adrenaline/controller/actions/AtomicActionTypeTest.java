package it.polimi.deib.newdem.adrenaline.controller.actions;

import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.MOVE1;
import static org.junit.Assert.*;

/**
 * Tests for {@code AtomicActionType}
 *
 * @see it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType
 */
public class AtomicActionTypeTest {

    /**
     * Tests get id functionality
     */
    @Test
    public void testGetId() {
        assertEquals(7, MOVE1.getId());
    }
}