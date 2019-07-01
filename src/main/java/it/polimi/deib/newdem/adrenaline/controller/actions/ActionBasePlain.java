package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicAction;
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

public abstract class ActionBasePlain implements Action {

    protected ActionDataSource listener;
    protected Game game;
    protected Player actor;
    private List<AtomicAction> atomicActions;

    public ActionBasePlain(Player actor, ActionDataSource actionDataSource, Game game) {
        this.actor = actor;
        this.listener = actionDataSource;
        this.game = game;
        this.atomicActions = new ArrayList<>();
    }

    @Override
    public void start() {
        for(AtomicAction atom : atomicActions) {
            try {
                atom.execute();
            }
            catch (UndoException e) {
                // terminate
            }
        }
    }

    @Override
    public Player getActor() {
        return actor;
    }
}
