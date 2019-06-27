package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;

import java.util.List;

public interface ActionBoard {

    List<ActionFactory> getBasicActions();

    int getIterations();

    void setListener(ActionBoardListener listener);

    void goFrenzy(boolean precedesFirstPlayer);

    boolean isFrenzy();
}
