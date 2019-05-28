package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public abstract class ActionBaseImpl implements Action {

    private ActionListener listener;

    protected Player actor;

    public ActionBaseImpl(Player actor) {
        // TODO implement
        this.actor = actor;
    }

    @Override
    public void bindListener(ActionListener listener) {
        // TODO implement
    }

    @Override
    public void start() {
        // TODO implement
    }

    @Override
    public Player getActor() {
        //TODO implement
        return null;
    }

    @Override
    public void emitGameChange(GameChange gameChange) {
        //TODO implement
    }
}
