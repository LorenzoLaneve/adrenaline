package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicAction;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomsContainer;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomsRunSequence;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomsRunSequenceImpl;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.ArrayList;
import java.util.List;

public class ActionContainer implements Action, AtomsContainer {

    protected ActionDataSource actionDataSource;
    protected Game game;
    protected Player actor;
    private List<AtomicAction> atomicActions;
    private AtomsRunSequence atomsRunSequence;

    public ActionContainer(Player actor, ActionDataSource actionDataSource) {
        this.actor = actor;
        this.actionDataSource = actionDataSource;
        this.game = actor.getGame();
        this.atomicActions = new ArrayList<>();
        this.atomsRunSequence = new AtomsRunSequenceImpl();
    }

    void addAtom(AtomicAction atomicAction) {
        atomicActions.add(atomicAction);
    }

    @Override
    public Player getActor() {
        return actor;
    }

    @Override
    public void start() throws UndoException {
        buildAtomsRunSequence();
        atomsRunSequence.executeFromStart();
    }

    private void buildAtomsRunSequence() {
        for(int i = 0; i < atomicActions.size(); i++) {
            atomsRunSequence.append(atomicActions.get(i));
        }
    }

    @Override
    public ActionDataSource getDataSource() {
        return actionDataSource;
    }

    @Override
    public Game getGame() {
        return game;
    }
}
