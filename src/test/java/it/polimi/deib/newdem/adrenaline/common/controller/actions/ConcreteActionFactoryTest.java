package it.polimi.deib.newdem.adrenaline.common.controller.actions;

import org.junit.Before;
import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.common.controller.actions.AtomicActionType.GRAB;
import static it.polimi.deib.newdem.adrenaline.common.controller.actions.AtomicActionType.MOVE1;
import static org.junit.Assert.*;

public class ConcreteActionFactoryTest {

    ActionFactory af;

    @Before
    public void setUp() {
        af = new ConcreteActionFactory(GRAB);
    }

    @Test
    public void testGetTypePositive() {
        assertEquals(new ActionType(GRAB) ,
                af.getType());
    }

    @Test
    public void testHashCodePositive() {
        assertEquals(af.hashCode(), GRAB.getId());
    }

    @Test
    public void testEqualsPositive() {
        assertEquals(af, new ConcreteActionFactory(GRAB));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTypeNegative() {
        new ConcreteActionFactory().getType();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHashCodeNegative() {
        new ConcreteActionFactory().hashCode();
    }

    @Test
    public void testEqualsNegative() {
        assertNotEquals(af, new ConcreteActionFactory(MOVE1));
        assertNotEquals(af, new Object());
        assertNotEquals(af, null);
    }
}