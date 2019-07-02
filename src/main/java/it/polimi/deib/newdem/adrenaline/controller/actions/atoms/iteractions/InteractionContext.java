package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomContext;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomsContainer;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectContext;

public interface InteractionContext extends AtomsContainer {

    void pushInteraction(Interaction interaction);

    EffectContext getEffectContext();
}
