package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionDataSource;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomEffectContext;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomsContainer;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class InteractionStackImpl implements InteractionContext, InteractionStack {

    private Deque<Interaction> runningStack;
    private AtomsContainer ancestor;
    private AtomEffectContext effectContext;

    public InteractionStackImpl(AtomsContainer ancestor) {
        this.runningStack = new ArrayDeque<>();
        this.ancestor = ancestor;
        this.effectContext = null;
    }

    @Override
    public void resolve() throws UndoException {
        Interaction top;
        Interaction oldTop = null;
        while (!runningStack.isEmpty()) {
            try {
                top = runningStack.getFirst();
                if(top == oldTop) {
                    break;
                }
                oldTop = top;
                top.execute();
            }
            catch (UndoException e) {
                // try to apply undo within this stack
                runningStack.removeFirst(); // remove current interrupted interaction
                // note: getFirst === peek, but throws exception
                // https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html
                revertUntilFirstInteractionWithInput();
            }
        }
    }

    // gattini;

    @Override
    public void revisit() throws UndoException {

        // undo until first interaction w/ input
        revertUntilFirstInteractionWithInput();

        Interaction top;
        Interaction oldTop = null;
        while (!runningStack.isEmpty()) {
            try {
                top = runningStack.getFirst();
                if(top == oldTop) {
                    break;
                }
                oldTop = top;
                top.execute();
            }
            catch (UndoException e) {
                // try to apply undo within this stack
                runningStack.removeFirst(); // remove current interrupted interaction
                // note: getFirst    === peek, but throws exception
                // note: removeFirst === pop, but throes exception
                // https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html
                revertUntilFirstInteractionWithInput();
            }
        }
    }

    private void revertUntilFirstInteractionWithInput() throws UndoException {
        try {
            Interaction undoTop = runningStack.getFirst(); // start looking for non.interactive interactions to remove

            while (!undoTop.requiresInput()) {
                undoTop.revert();
                runningStack.removeFirst();
                undoTop = runningStack.getFirst();
            }

            undoTop.revert();
        }
        catch(NoSuchElementException x) {
            // all interactions have been undone
            // propagate undo to atom container
            throw new UndoException();
        }
    }

    @Override
    public void push(Interaction interaction) {
        runningStack.push(interaction);
    }

    @Override
    public void pushInteraction(Interaction interaction) {
        runningStack.push(interaction);
    }

    @Override
    public Player getActor() {
        return ancestor.getActor();
    }

    @Override
    public ActionDataSource getDataSource() {
        return ancestor.getDataSource();
    }

    @Override
    public Game getGame() {
        return ancestor.getGame();
    }

    @Override
    public void registerContext(AtomEffectContext context) {
        this.effectContext = context;
    }

    @Override
    public AtomEffectContext getEffectContext() {
        return effectContext;
    }
}
