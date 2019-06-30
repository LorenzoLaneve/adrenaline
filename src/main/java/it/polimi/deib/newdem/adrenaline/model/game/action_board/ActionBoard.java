package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;

import java.util.List;

public interface ActionBoard {

    List<ActionFactory> getBasicActions();

    /**
     * @return Numbers of actions to do in a turn.
     */
    int getIterations();

    void setListener(ActionBoardListener listener);

    /**
     * Changes the state of the board.
     * @param precedesFirstPlayer player before the first in the frenzy turn.
     */
    void goFrenzy(boolean precedesFirstPlayer);

    boolean isFrenzy();
}
