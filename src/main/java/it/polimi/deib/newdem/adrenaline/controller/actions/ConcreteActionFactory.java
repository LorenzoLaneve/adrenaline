package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.*;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointType;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;


public class ConcreteActionFactory implements ActionFactory {

    private ActionType actionType;

    public ConcreteActionFactory(ActionType type) {
        this.actionType = type;
    }


    public ConcreteActionFactory(AtomicActionType...atoms) {
        this.actionType = new ActionType(atoms);
    }

    @Override
    public Action makeAction(Player actor, TurnDataSource dataSource) {
        // make container
        ActionContainer container = new ActionContainer(actor, dataSource);

        // fill container with ordered atoms from atomic action types
        AtomicAction currentAtomicAction = null;
        for (AtomicActionType aat : actionType.getAtomicTypes()) {
            EntryPointFactory epfm = new EntryPointFactory(EntryPointType.MOVEMENT);
            epfm.setMinDist(0);

            switch (aat) {
                case MOVE1:
                    epfm.setMaxDist(1);
                    currentAtomicAction = new MoveAtom(container, epfm);
                    break;
                case MOVE2:
                    epfm.setMaxDist(2);
                    currentAtomicAction = new MoveAtom(container, epfm);
                    break;
                case MOVE3:
                    epfm.setMaxDist(3);
                    currentAtomicAction = new MoveAtom(container, epfm);
                    break;
                case MOVE4:
                    epfm.setMaxDist(4);
                    currentAtomicAction = new MoveAtom(container, epfm);
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
                case RELOAD:
                    currentAtomicAction = new ReloadAtom(container);
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
