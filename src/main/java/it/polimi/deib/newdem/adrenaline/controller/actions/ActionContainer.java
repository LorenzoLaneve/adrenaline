package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicAction;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomsContainer;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;

import java.util.ArrayList;
import java.util.List;

public class ActionContainer implements Action, AtomsContainer {

    protected ActionDataSource actionDataSource;
    protected Game game;
    protected Player actor;
    private List<AtomicAction> atomicActions;

    public ActionContainer(Player actor, ActionDataSource actionDataSource) {
        this.actor = actor;
        this.actionDataSource = actionDataSource;
        this.game = actor.getGame();
        this.atomicActions = new ArrayList<>();
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
        for(AtomicAction atom : atomicActions) {
            try {
                atom.execute();
            }
            catch (ConnectionException e) {
                // do what now
            }
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
