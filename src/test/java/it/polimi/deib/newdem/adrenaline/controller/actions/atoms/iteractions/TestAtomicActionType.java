package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicAction;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAtomicActionType {

    @Test
    public void testMoveDistance() {
        assertEquals(1, AtomicActionType.MOVE1.getMoveDistance());
        assertEquals(2, AtomicActionType.MOVE2.getMoveDistance());
        assertEquals(3, AtomicActionType.MOVE3.getMoveDistance());
        assertEquals(4, AtomicActionType.MOVE4.getMoveDistance());
    }

    @Test
    public void testCovers() {
        assertTrue(AtomicActionType.MOVE3.covers(AtomicActionType.MOVE1));
        assertFalse(AtomicActionType.MOVE1.covers(AtomicActionType.MOVE3));
    }

}
