package it.polimi.deib.newdem.adrenaline.controller.actions;

import org.junit.Before;
import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.GRAB;
import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.MOVE1;
import static org.junit.Assert.*;

/**
 * Tests for {@code ConcreteActionFactory}
 *
 * @see ConcreteActionFactory
 */
public class ConcreteActionFactoryTest {

    private ActionFactory af;

    /**
     * Allocates a new {@code ConcreteActionFactory}
     */
    @Before
    public void setUp() {
        af = new ConcreteActionFactory(GRAB);
    }

    /**
     * Tests the get type functionality
     */
    @Test
    public void testGetTypePositive() {
        assertEquals(new ActionType(GRAB) ,
                af.getType());
    }

    /**
     * Tests the hash code functionality
     */
    @Test
    public void testHashCodePositive() {
        assertEquals(af.hashCode(), GRAB.getId());
    }

    /**
     * Tests the equals functionality
     */
    @Test
    public void testEqualsPositive() {
        assertEquals(af, new ConcreteActionFactory(GRAB));
    }

    /**
     * Tests a fail case for the get type functionality
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetTypeNegative() {
        new ConcreteActionFactory().getType();
    }

    /**
     * Tests a fail case for the hash code functionality
     */
    @Test(expected = IllegalArgumentException.class)
    public void testHashCodeNegative() {
        new ConcreteActionFactory().hashCode();
    }

    /**
     * Tests a fail case for the equals functionality
     */
    @Test
    public void testEqualsNegative() {
        assertNotEquals(af, new ConcreteActionFactory(MOVE1));
        assertNotEquals(af, new Object());
        assertNotEquals(af, null);
    }
}