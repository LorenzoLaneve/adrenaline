package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomsContainer;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;

public class MockAtomsContainer implements AtomsContainer {

    @Override
    public Player getActor() {
        return null;
    }

    @Override
    public TurnDataSource getDataSource() {
        return null;
    }

    @Override
    public Game getGame() {
        return null;
    }
}
