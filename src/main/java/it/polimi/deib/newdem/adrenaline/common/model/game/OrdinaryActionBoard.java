package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionFactory;

import java.util.List;

public class OrdinaryActionBoard implements ActionBoard {

    /**Returns all the {@code ActionFactories} for legal {@code Action}s
     *
     * @return the {@code ActionFactories}
     */
    @Override
    public List<ActionFactory> getBasicActions() {
        // TODO implement
        return null;
    }

    /**
     * Retrieves the total number of legal moves this turn.
     *
     * @return total number of moves
     */
    @Override
    public int getIterations() {
        return 2;
    }
}
