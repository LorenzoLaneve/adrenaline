package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class AtomsRunSequenceImpl implements AtomsRunSequence {
    // not really a stack
    // more like list
    // meaning it can't change itself during execution
    // ASF?
    private ArrayList<AtomicAction> atomsList;

    public AtomsRunSequenceImpl() {
        this.atomsList = new ArrayList<>();
    }

    @Override
    public void append(AtomicAction atom) {
        atomsList.add(atom);
    }

    @Override
    public void executeFromStart() throws UndoException {
        /*AtomicAction top;
        AtomicAction oldTop = null;
        */

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

        /*
        while (!atomsStack.isEmpty()) {
            try {
                top = atomsStack.getFirst();
                if(top == oldTop) {
                    break;
                }
                oldTop = top;
                top.executeFromStart();
            }
            catch (UndoException e) {
                // try to apply undo within this stack
                atomsStack.removeFirst(); // remove current interrupted interaction
                // note: getFirst === peek, but throws exception
                // https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html
                // no need for revertUntilFirstInteractionWithInput();
                // go back once is sufficient (removeFirst above)
            }
            catch (NoSuchElementException x) {
                throw new UndoException();
            }
        }
        */
    }
}
