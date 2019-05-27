package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;

import java.util.ArrayList;
import java.util.List;

public class FrenzyDoubleActionBoardBehavior extends FrenzyCommonBehavior {
    @Override
    public List<ActionFactory> getBasicActions() {
        List<ActionFactory> factories = new ArrayList<>(3);
        factories.add(new ConcreteActionFactory(
                AtomicActionType.MOVE1,
                AtomicActionType.RELOAD,
                AtomicActionType.SHOOT
        ));
        factories.add(new ConcreteActionFactory(
                AtomicActionType.MOVE4
        ));
        factories.add(new ConcreteActionFactory(
                AtomicActionType.MOVE2,
                AtomicActionType.GRAB
        ));
        //TODO listeners?
        return factories;
    }

    @Override
    public int getIterations() {
        return 2;
    }
}
