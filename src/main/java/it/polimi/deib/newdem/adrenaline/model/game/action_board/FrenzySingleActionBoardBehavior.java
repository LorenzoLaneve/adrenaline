package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Concrete behavior for the strategy in {@code ActionBoardImpl}
 */
public class FrenzySingleActionBoardBehavior extends FrenzyCommonBehavior {

    @Override
    public List<ActionFactory> getBasicActions() {
        List<ActionFactory> factories = new ArrayList<>(2);
        factories.add(new ConcreteActionFactory(
                AtomicActionType.MOVE2,
                AtomicActionType.RELOAD,
                AtomicActionType.SHOOT));
        factories.add(new ConcreteActionFactory(
                AtomicActionType.MOVE3,
                AtomicActionType.GRAB
        ));

        return factories;
    }

    @Override
    public int getIterations() {
        return 1;
    }
}
