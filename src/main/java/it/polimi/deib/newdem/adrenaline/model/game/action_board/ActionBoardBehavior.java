package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;

import java.util.List;

/**
 * Strategy pattern used in ActionBoardImpl
 * @see ActionBoardImpl to see how this interface is used.
 */
public interface ActionBoardBehavior {

    List<ActionFactory> getBasicActions();

    /**
     * @return Numbers of actions to do in a turn.
     */
    int getIterations();

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
