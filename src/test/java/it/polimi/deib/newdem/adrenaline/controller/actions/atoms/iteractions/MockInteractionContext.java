package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomEffectContext;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;

public class MockInteractionContext implements InteractionContext, InteractionStack {

    @Override
    public void pushInteraction(Interaction interaction) {

    }

    @Override
    public AtomEffectContext getEffectContext() {
        return new NullAtomEffectContext();
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

    @Override
    public void resolve() throws UndoException {

    }

    @Override
    public void push(Interaction interaction) {

    }

    @Override
    public void revisit() throws UndoException {

    }

    @Override
    public void registerContext(AtomEffectContext context) {

    }
}
