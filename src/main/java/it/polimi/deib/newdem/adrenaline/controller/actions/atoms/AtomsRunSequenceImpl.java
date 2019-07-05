package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;

import java.util.ArrayList;

/**
 * A simple implementation of {@code AtomsRunSequence} based on a variation on Finite-State Automata
 */
public class AtomsRunSequenceImpl implements AtomsRunSequence {

    private ArrayList<AtomicAction> atomsList;

    /**
     * Builds a new {@code AtomsRunSequence} with not atoms in it
     */
    public AtomsRunSequenceImpl() {
        this.atomsList = new ArrayList<>();
    }

    @Override
    public void append(AtomicAction atom) {
        atomsList.add(atom);
    }

    @Override
    public void executeFromStart() throws UndoException {

        int cursor = 0;
        boolean lastTerminationGraceful = true;
        while (cursor < atomsList.size()) {
            try{
                AtomicAction currentAtom = atomsList.get(cursor);
                if(lastTerminationGraceful) {
                    currentAtom.executeFromStart();
                }
                else {
                    currentAtom.executeFromLatest();
                }
                // if ok
                lastTerminationGraceful = true;
                cursor++;
            }
            catch (UndoException e) {
                // if undo
                lastTerminationGraceful = false;
                cursor--;
                if(cursor < 0) throw new UndoException();
            }
        }
    }
}
