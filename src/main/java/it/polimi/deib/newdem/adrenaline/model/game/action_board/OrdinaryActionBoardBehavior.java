package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;

import java.util.ArrayList;
import java.util.List;

public class OrdinaryActionBoardBehavior implements ActionBoardBehavior {

    @Override
    public List<ActionFactory> getBasicActions() {
        List<ActionFactory> factories = new ArrayList<>();
        factories.add(new ConcreteActionFactory(AtomicActionType.MOVE3));
        factories.add(new ConcreteActionFactory(AtomicActionType.MOVE1,
                AtomicActionType.GRAB));
        factories.add(new ConcreteActionFactory(AtomicActionType.SHOOT));

        return factories;
    }

    @Override
    public int getIterations() {
        return 2;
    }

    @Override
    public void onEnter(ActionBoardImpl actionBoard) {
        // do nothing
        // TODO notify listener of existence?
    }

    @Override
    public void onLeave(ActionBoardImpl actionBoard) {
        // also do nothing
    }

    @Override
    public boolean isFrenzy() {
        return false;
    }
}
