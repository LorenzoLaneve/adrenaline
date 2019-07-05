package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomContext;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectContext;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.MockPlayer;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import org.junit.Test;

import java.util.Arrays;

public class TestAtomContext {

    @Test(expected = UndoException.class)
    public void tetExecuteLatest() throws Exception {
            TestableEffectContext.make().executeFromLatest();
    }
    @Test
    public void testRevertGc() {
        TestableEffectContext.make().revertGameChange(new MockGameChange());
    }

    @Test
    public void testGetAttacker() {
        TestableEffectContext.make().getAttacker();
    }

    @Test
    public void testGetVictim() {
        TestableEffectContext.make().getVictim();
    }

    @Test
    public void testSetVictim() throws Exception {
        TestableEffectContext.make().setVictim(new MockPlayer());
    }

    @Test
    public void testChooseFragment() throws Exception {
        try {
            TestableEffectContext.make().chooseFragment(Arrays.asList(1, 2));
        }
        catch (NullPointerException e) {
            // ok
        }
    }

    @Test
    public void testTriggers() {
        AtomContext ec = TestableEffectContext.make();

        ec.setEnableDamageTriggers(false);
        ec.damageDealtTrigger(new MockPlayer(), new MockPlayer());
        ec.damageTakenTrigger(new MockPlayer(), new MockPlayer());

        ec.setEnableDamageTriggers(false);
        ec.damageDealtTrigger(new MockPlayer(), new MockPlayer());
        ec.damageTakenTrigger(new MockPlayer(), new MockPlayer());
    }

    @Test
    public void testGetEffectContext() throws Exception {
        TestableEffectContext.make().getEffectContext();
    }
}
