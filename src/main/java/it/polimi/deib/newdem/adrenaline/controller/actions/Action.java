package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Control element of the game. This interface offers simple methods to model
 */
public interface Action {

    /**
     * Returns the player that is executing this action.
     */
    Player getActor();

    /**
     * Starts to executeFromStart the action.
     * @throws UndoException if the user requested to undo the action.
     */
    void start() throws UndoException;

}
