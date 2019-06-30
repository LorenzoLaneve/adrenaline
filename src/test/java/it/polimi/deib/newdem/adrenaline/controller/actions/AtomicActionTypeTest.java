package it.polimi.deib.newdem.adrenaline.controller.actions;

import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.MOVE1;
import static org.junit.Assert.*;

public class AtomicActionTypeTest {

    @Test
    public void testGetId() {
        assertEquals(7, MOVE1.getId());
    }
}