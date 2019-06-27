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

public abstract class ActionBaseImpl implements Action, EffectContext {

    protected ActionDataSource listener;
    protected Game game;
    protected Player actor;
    private List<AtomicAction> atomicActions;

    public ActionBaseImpl(Player actor, ActionDataSource actionDataSource, Game game) {
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
            catch (ConnectionException e) {
                // terminate
            }
        }
    }

    @Override
    public Player getActor() {
        return actor;
    }


    // deprecated
    /*
    @Override
    public Effect getEffect() {
        return null;
    }
    */

    @Override
    public void applyGameChange(GameChange gameChange) {
        gameChange.update(actor.getGame());
    }

    @Override
    public void revertGameChange(GameChange gameChange) {
        gameChange.revert(actor.getGame());
    }

    @Override
    public Player getAttacker() {
        return actor;
    }

    // more than 1 victim?
    // scope / grenade?
    @Override
    public Player getVictim() {
        return null;
    }

    @Override
    public Player choosePlayer(MetaPlayer player, PlayerSelector selector, boolean forceChoice) throws UndoException {
        return null;
    }

    @Override
    public Tile chooseTile(TileSelector selector, boolean forceChoice) throws UndoException {
        return null;
    }

    @Override
    public Integer chooseFragment(List<Integer> choices) throws UndoException {
        return null;
    }

    @Override
    public PaymentReceipt choosePayment(PaymentInvoice price, Integer choice) throws UndoException {
        return null;
    }
}
