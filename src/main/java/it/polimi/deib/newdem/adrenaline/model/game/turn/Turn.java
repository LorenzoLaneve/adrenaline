package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public interface Turn {

    Player getActivePlayer();

    void execute();

    void bindDataSource(TurnDataSource turnDataSource);
}
