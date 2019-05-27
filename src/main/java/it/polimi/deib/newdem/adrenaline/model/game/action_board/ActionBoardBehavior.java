package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;

import java.util.List;

public interface ActionBoardBehavior {

    List<ActionFactory> getBasicActions();

    int getIterations();

    boolean isFrenzy();

    void onEnter(ActionBoardImpl actionBoard);

    void onLeave(ActionBoardImpl actionBoard);
}
