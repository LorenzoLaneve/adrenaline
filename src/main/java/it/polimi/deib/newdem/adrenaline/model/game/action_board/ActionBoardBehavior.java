package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;

import java.util.List;

/**
 * Strategy pattern used in ActionBoardImpl
 * @see ActionBoardImpl to see how this interface is used.
 */
public interface ActionBoardBehavior {

    /**
     * Retrieves the basic actions allowed by the current game state
     * @return allowed actions
     */
    List<ActionFactory> getBasicActions();

    /**
     * Retrieves the total number of actions to do in this turn
     * @return total number of actions to do in this turn
     */
    int getIterations();

    /**
     * Checks whether this {@code ActionBoard} is in frenzy mode
     * @return
     */
    boolean isFrenzy();

    /**
     *Run routines associated with setting a behavior.
     * @param actionBoard
     */
    void onEnter(ActionBoardImpl actionBoard);

    /**
     * Run routines associated with releasing a behavior.
     * @param actionBoard
     */
    void onLeave(ActionBoardImpl actionBoard);
}
