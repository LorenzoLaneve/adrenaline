package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;

public interface AtomicAction {

    void execute() throws ConnectionException;
}
