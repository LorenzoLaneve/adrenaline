package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.model.game.MockPowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.game.MockWeapon;
import it.polimi.deib.newdem.adrenaline.model.game.MockWeaponCard;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestInputReverse {

    @Test
    public void testRevert() throws Exception {

        testInteraction(new SelectShootWeaponInteraction(new MockInteractionContext()));
        testInteraction(new SelectDiscardWeaponInteraction(new MockInteractionContext(), new MockWeaponCard("")));
        testInteraction(new SelectShootWeaponInteraction(new MockInteractionContext()));
        testInteraction(new ResolveAdditionalDamageInteraction(new MockInteractionContext(), new MockPowerUpCard()));
        testInteraction(new ReloadActivationInteraction(new MockInteractionContext(), new MockWeaponCard("")));
        testInteraction(new AdditionalDamageInteraction(new MockInteractionContext()));
        testInteraction(new SelectRevengePupInteraction(new MockInteractionContext()));
        testInteraction(new DischargeInteraction(new MockInteractionContext(), new MockWeapon("")));
        testInteraction(new ActivatePupInteraction(new MockInteractionContext(), new MockPowerUpCard()));
        testInteraction(new RequireDiscardInteraction(new MockInteractionContext(), new MockWeaponCard("")));
        testInteraction(new RevengeEffectApplicationIneraction(new MockInteractionContext(), new MockPowerUpCard()));


    }

    private void testInteraction(Interaction interaction) {
        interaction.revert();
        interaction.requiresInput();
    }
}
