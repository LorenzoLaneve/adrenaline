package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public interface Action {

    /**
     * Returns the player that is executing this action.
     */
    Player getActor();

    /**
     * Returns the effect this action is executing.
     */
    // deprecated
    //Effect getEffect();

    /**
     * Starts to execute the action.
     * @throws UndoException if the user requested to undo the action.
     */
    void start() throws UndoException;

    // allow skinny actions without EffectVisitor
    // implement within start()?
}
