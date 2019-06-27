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

import java.util.ArrayList;
import java.util.List;

public class ActionContainer implements Action, AtomsContainer, EffectContext {

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
        // TODO implement?
    }

    @Override
    public ActionDataSource getDataSource() {
        return actionDataSource;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void applyGameChange(GameChange gameChange) {
        gameChange.update(game);
    }

    @Override
    public void revertGameChange(GameChange gameChange) {
        gameChange.revert(game);
    }

    @Override
    public Player getAttacker() {
        // TODO ???
        // attacker is always the actor?
        //
        return actor;
        // return null;
    }

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

    @Override
    public void damageDealtTrigger(Player attacker, Player victim) {

    }

    @Override
    public void damageTakenTrigger(Player attacker, Player victim) {

    }
}
