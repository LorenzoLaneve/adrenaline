package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;

/**
 * Exposes the functionality that an atom may require to its container.
 */
public interface AtomsContainer {

    /**
     * Retrieve the actor of the current container
     *
     * @return actor of the current container
     */
    Player getActor();

    /**
     * Retrieve the {@code TurnDataSource} of the current container
     * @return {@code TurnDataSource} of the current container
     */
    TurnDataSource getDataSource();


    /**
     * Retrieve the {@code Game} of the current container
     *
     * @return container
     */
    Game getGame();

}
