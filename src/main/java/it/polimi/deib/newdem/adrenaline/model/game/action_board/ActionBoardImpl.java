package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;

import java.util.List;

public class ActionBoardImpl implements ActionBoard {

    private ActionBoardBehavior behavior;
    private ActionBoardListener listener;

    /**
     * Builds a new {@code ActionBoard} with ordinary (non-frenzy) behavior.
     */
    public ActionBoardImpl() {
        this.behavior = new OrdinaryActionBoardBehavior();
        this.listener = new NullActionBoardListener();
    }

    /**
     * Registers the given listener
     *
     * @param listener which will be registered
     */
    public void setListener(ActionBoardListener listener) {

        this.listener = listener;
    }

    /**
     * Retrieves the basic actions allowed by this boards
     *
     * @return allowed actions
     */
    @Override
    public List<ActionFactory> getBasicActions() {
        return behavior.getBasicActions();
    }

    /**
     * Retrieves the amount of moves allowed by this board
     *
     * @return amount of allowed moves
     */
    @Override
    public int getIterations() {
        return behavior.getIterations();
    }

    /**
     * Changes this board's behavior to frenzy if it isn't already.
     *
     * @throws IllegalStateException if this board is already in frenzy behavior.
     * @param precedesFirstPlayer determines which set of actions and iterations the new behavior will have
     */
    @Override
    public void goFrenzy(boolean precedesFirstPlayer) {
        if(behavior.isFrenzy()) throw new IllegalStateException();
        behavior.onLeave(this);
        behavior = precedesFirstPlayer ?
                new FrenzyDoubleActionBoardBehavior() :
                new FrenzySingleActionBoardBehavior();
        behavior.onEnter(this);
    }

    /**
     * Notifies the assigned listener, if it exists, of the change to frenzy behavior.
     */
    void notifyFlip() {
        listener.boardDidFlip();
    }

    @Override
    public boolean isFrenzy() {
        return behavior.isFrenzy();
    }
}
