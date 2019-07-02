package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectContext;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;

public interface AtomicAction {

    void executeFromStart() throws UndoException;

    void executeFromLatest() throws UndoException;

    EffectContext getEffectContext();
}
