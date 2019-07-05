package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionDataSource;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

/**
 * A source of additional data for any {@code Effect}, {@code Action} or {@code Interaction} that may
 * require them during a turn.
 *
 * This class can require inputs to humans through the network.
 */
public interface TurnDataSource extends ActionDataSource {

    /**
     * Requests and retrieves an action from a given list
     *
     * @param actionTypeList list of legal actions to choose from
     * @return chosen action type
     * @throws UndoException if the user chooses to terminate their turn
     */
    ActionType requestAction(List<ActionType> actionTypeList) throws UndoException;

    /**
     * Pushes a new {@code Player} to this {@code TurnDataSource}
     *
     * This triggers the opportune listeners to notify the user that their
     * turn has begun
     *
     * @param actor player whose turn began
     */
    void pushActor(Player actor);

    /**
     * Pops {@code Player} from this {@code TurnataSource}.
     *
     * This triggers the opportune listeners to notify the user that their turn
     * is over
     *
     * @param actor player whose turn is over
     */
    void popActor(Player actor);

    /**
     * Retrieves the active player
     *
     * @return active player
     */
    Player peekActor();

}
