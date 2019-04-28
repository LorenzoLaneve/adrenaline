package it.polimi.deib.newdem.adrenaline.common.controller.actions;

import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.common.controller.actions.AtomicActionType.MOVE1;
import static org.junit.Assert.*;

public class AtomicActionTypeTest {

    @Test
    public void testGetId() {
        assertEquals(7, MOVE1.getId());
    }
}