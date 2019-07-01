package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.view.inet.events.TurnFragmentRequest;

public interface Turn {

    Player getActivePlayer();

    void execute();

    void bindDataSource(TurnDataSource turnDataSource);

    TurnDataSource getDataSource();
}
