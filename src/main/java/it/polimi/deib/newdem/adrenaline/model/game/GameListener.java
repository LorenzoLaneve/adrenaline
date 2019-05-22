package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

public interface GameListener {

    void gameWillStart(Game game);

    void gameWillEnd(Game game);

    void userDidEnterGame(User user, Player player);

    void userDidExitGame(User user, Player player);

}
