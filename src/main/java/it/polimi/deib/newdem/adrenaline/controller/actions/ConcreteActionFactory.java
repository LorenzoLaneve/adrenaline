package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;


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
        // make container
        ActionContainer container = new ActionContainer(actor, actionDataSource);

        // fill container with ordered atoms from atomic action types
        AtomicAction currentAtomicAction = null;
        for (AtomicActionType aat : actionType.getAtomicTypes()) {
            switch (aat) {
                case MOVE1:
                    currentAtomicAction = new MoveAtom(container, 0,1);
                    break;
                case MOVE2:
                    currentAtomicAction = new MoveAtom(container, 0,2);
                    break;
                case MOVE3:
                    currentAtomicAction = new MoveAtom(container, 0,3);
                    break;
                case MOVE4:
                    currentAtomicAction = new MoveAtom(container, 0,4);
                    break;
                case SHOOT:
                    currentAtomicAction = new ShootAtom(container);
                    break;
                case GRAB:
                    currentAtomicAction = new GrabAtom(container);
                    break;
                case USE_POWERUP:
                    currentAtomicAction = new PowerUpAtom(container);
                    break;
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
