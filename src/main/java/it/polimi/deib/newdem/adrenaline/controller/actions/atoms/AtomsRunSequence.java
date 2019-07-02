package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;

public interface AtomsRunSequence {

    void append(AtomicAction atom);

    void executeFromStart() throws UndoException;
}
