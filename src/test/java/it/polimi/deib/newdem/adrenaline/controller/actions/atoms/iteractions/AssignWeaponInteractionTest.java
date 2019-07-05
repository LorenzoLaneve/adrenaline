package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.model.game.MockWeapon;
import it.polimi.deib.newdem.adrenaline.model.game.MockWeaponCard;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Unit test for {@code DischargeWeaponInteraction}
 *
 * @see it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.DischargeInteraction
 */
public class AssignWeaponInteractionTest {

    private AssignWeaponInteraction interaction;

    /**
     * Creates a new interaction
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        interaction = new AssignWeaponInteraction(
                new MockInteractionContext(), 
                new MockWeaponCard("gun"), 
                new MockWeapon("knife"));
    }

    /**
     * Tests the revert functionality
     *
     * @throws Exception
     */
    @Test
    public void testRevert() throws Exception {
        interaction.revert();
    }

    /**
     * Tests the requires input functionality
     * @throws Exception
     */
    @Test
    public void testRequiresInput() throws Exception {
        assertTrue(interaction.requiresInput());
    }
}
