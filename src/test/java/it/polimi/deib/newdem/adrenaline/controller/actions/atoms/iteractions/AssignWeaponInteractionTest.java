package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.model.game.MockWeapon;
import it.polimi.deib.newdem.adrenaline.model.game.MockWeaponCard;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class AssignWeaponInteractionTest {

    AssignWeaponInteraction interaction;

    @Before
    public void setUp() throws Exception {
        interaction = new AssignWeaponInteraction(
                new MockInteractionContext(), 
                new MockWeaponCard("gun"), 
                new MockWeapon("knife"));
    }

    @Test
    public void testRevert() throws Exception {
        interaction.revert();
    }

    @Test
    public void testRequiresInput() throws Exception {
        assertTrue(interaction.requiresInput());
    }
}
