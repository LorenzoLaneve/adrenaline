package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;

import java.util.List;

/**
 * An action board is an object representing the board of a player
 * defining the possible actions that can be made during a turn.
 */
public interface ActionBoard {

    /**
     * Returns a list of actions provided by the action board in case of zero damage taken.
     */
    List<ActionFactory> getBasicActions();

    /**
     * @return Numbers of actions to do in a turn.
     */
    int getIterations();

    /**
     * Assigns the given {@code ActionBoardListener} to this {@code DamageBoard}
     * @param listener to assign to this board
     */
    void setListener(ActionBoardListener listener);

    /**
     * Changes the state of the board.
     * @param precedesFirstPlayer player before the first in the frenzy turn.
     */
    void goFrenzy(boolean precedesFirstPlayer);

    /**
     * Returns whether this action board is used in frenzy mode.
     */
    boolean isFrenzy();

}
