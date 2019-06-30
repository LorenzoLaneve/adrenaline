package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionDataSource;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public interface AtomsContainer {
    Player getActor();
    ActionDataSource getDataSource();
    Game getGame();
}
