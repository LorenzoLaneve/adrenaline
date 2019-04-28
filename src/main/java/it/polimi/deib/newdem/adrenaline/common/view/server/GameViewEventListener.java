package it.polimi.deib.newdem.adrenaline.common.view.server;

import it.polimi.deib.newdem.adrenaline.common.model.game.Game;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

public interface GameViewEventListener {

    void playerWillExitGame(Player player, Game game);

}
