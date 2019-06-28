package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicAction;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.MoveAtom;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.ShootAtom;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;

import java.util.ArrayList;


public class ConcreteActionFactory implements ActionFactory {

    private ActionType actionType;

    public ConcreteActionFactory(ActionType type) {
        this.actionType = type;
    }


    public ConcreteActionFactory(AtomicActionType...atoms) {
        this.actionType = new ActionType(atoms);
    }

    @Override
    public Action makeAction(Player actor, ActionDataSource actionDataSource) {
        //TODO implement
        // make container
        ActionContainer container = new ActionContainer(actor, actionDataSource);

        // fill container with ordered atoms from atomic action types
        AtomicAction currentAtomicAction = null;
        // TODO redo w/ monolithic actions
        for (AtomicActionType aat : actionType.getAtomicTypes()) {
            switch (aat) {
                case MOVE1:
                    currentAtomicAction = new MoveAtom(container, 0,1);
                    break;
                case SHOOT:
                    currentAtomicAction = new ShootAtom(container);
                    break;
                // TODO add cases
                default:
                    currentAtomicAction = null;
                    break;
            }
            container.addAtom(currentAtomicAction);
        }

        // done
        return container;
    }

    @Override
    public ActionType getType() {
        return actionType;
    }

    @Override
    public int hashCode() {
        return actionType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ConcreteActionFactory)) return false;
        return this.actionType.equals(((ConcreteActionFactory) obj).getType());
    }
}
