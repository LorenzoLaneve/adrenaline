package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.common.controller.actions.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.common.controller.actions.ConcreteActionFactory;

import java.util.ArrayList;
import java.util.List;

public class FrenzySingleActionBoard implements ActionBoard {

    /**
     * Returns the basic actions for this damageBoard
     *
     * @return allowed actions
     */
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
        //TODO listeners?
        return factories;
    }

    /**
     * Returns the total number of allowed moves for this {@code DamageBoard}
     *
     * @return total number of moves
     */
    @Override
    public int getIterations() {
        return 1;
    }
}
