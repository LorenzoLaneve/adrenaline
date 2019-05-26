package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;

import java.util.ArrayList;
import java.util.List;

public class FrenzyDoubleActionBoard implements ActionBoard {



    /**
     * Returns the basic actions for this damageBoard
     *
     * @return allowed actions
     */
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

    /**
     * Returns the total number of allowed moves for this {@code DamageBoard}
     *
     * @return total number of moves
     */
    @Override
    public int getIterations() {
        return 2;
    }

    @Override
    public void boardDidFlip() {
        // TODO absorb in superclass
    }
}
