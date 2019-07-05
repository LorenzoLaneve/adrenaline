package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomEffectContext;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;

import java.util.List;

/**
 * Mock object for testing purposes
 *
 * All of its methods do nothing
 */
public class MockInteractionContext implements InteractionContext {

    @Override
    public void pushInteraction(Interaction interaction) {

    }

    @Override
    public AtomEffectContext getEffectContext() {
        return null;
    }

    @Override
    public List<Player> getDamagedPlayers() {
        return null;
    }

    @Override
    public void setVictim(Player victim) {

    }

    @Override
    public Player getVictim() {
        return null;
    }

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
