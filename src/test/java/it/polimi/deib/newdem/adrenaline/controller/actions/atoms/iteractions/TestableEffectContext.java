package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomContext;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomEffectContext;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomsContainer;
import org.junit.Test;

import java.util.Arrays;

public class TestableEffectContext extends AtomContext {

    public TestableEffectContext(AtomsContainer parent, EntryPointType entryPointType) {
        super(parent, entryPointType);
    }

    public static TestableEffectContext make() {
        return new TestableEffectContext(new MockInteractionContext(), EntryPointType.GRAB);
    }
}
