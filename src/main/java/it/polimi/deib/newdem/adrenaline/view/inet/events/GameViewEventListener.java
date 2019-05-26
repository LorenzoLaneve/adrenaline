package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.Player;

public interface GameViewEventListener {

    void playerWillExitGame(Player player, Game game);

}
