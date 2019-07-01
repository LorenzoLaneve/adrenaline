package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.view.inet.events.TurnFragmentRequest;

public interface Turn {

    Player getActivePlayer();

    void execute();

    /**
     * Assign to the turn the source of user input.
     * @param turnDataSource source of user input.
     */
    void bindDataSource(TurnDataSource turnDataSource);

    TurnDataSource getDataSource();
}
