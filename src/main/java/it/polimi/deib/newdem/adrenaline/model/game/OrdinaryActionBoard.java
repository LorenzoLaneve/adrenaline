package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;

import java.util.ArrayList;
import java.util.List;

public class OrdinaryActionBoard implements ActionBoard {

    /**Returns all the {@code ActionFactories} for legal {@code Action}s
     *
     * @return the {@code ActionFactories}
     */
    @Override
    public List<ActionFactory> getBasicActions() {

        List<ActionFactory> factories = new ArrayList<>();
        factories.add(new ConcreteActionFactory(AtomicActionType.MOVE3));
        factories.add(new ConcreteActionFactory(AtomicActionType.MOVE1,
                AtomicActionType.GRAB));
        factories.add(new ConcreteActionFactory(AtomicActionType.SHOOT));

        return factories;
    }

    /**
     * Retrieves the total number of legal moves this turn.
     *
     * @return total number of moves
     */
    @Override
    public int getIterations() {
        return 2;
    }
}
