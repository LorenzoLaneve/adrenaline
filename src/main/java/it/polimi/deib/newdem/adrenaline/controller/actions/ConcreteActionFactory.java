package it.polimi.deib.newdem.adrenaline.controller.actions;

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
    public Action makeAction(Player actor) {
        //TODO implement
        return null;
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
