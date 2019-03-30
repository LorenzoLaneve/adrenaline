package it.polimi.deib.newdem.adrenaline.common.mechs.actions;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.effects.Effect;

public abstract class ActionBaseImpl implements Action {

    private ActionListener listener;

    private Player actor;

    public ActionBaseImpl(Player actor) {
        // TODO implement
        this.actor = actor;
    }


    @Override
    public Effect getEffect() {
        // TODO implement
        return null;
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
    public void unbindListener(ActionListener listener) {
        // TODO implement
    }
}
