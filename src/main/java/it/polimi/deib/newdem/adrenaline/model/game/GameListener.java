package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public interface GameListener {

    void gameDidInit(Game game, GameData gameData);

    void gameWillEnd(Game game);

    void userDidEnterGame(User user, Player player);

    void userDidExitGame(User user, Player player);

}
