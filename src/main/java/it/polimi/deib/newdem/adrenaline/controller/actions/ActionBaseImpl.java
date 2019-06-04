package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public class ActionBaseImpl extends EffectVisitorBase implements Action {

    private ActionDataSource listener;

    private Player actor;


    public ActionBaseImpl(Player actor) {
        this.actor = actor;
    }

    @Override
    public void start() throws UndoException {
        visit(getEffect());
    }

    @Override
    public Player getActor() {
        return actor;
    }

    @Override
    public Effect getEffect() {
        return null;
    }


    @Override
    public Player askForPlayer(MetaPlayer player, PlayerSelector selector, boolean mandatory) throws UndoException {
        // TODO
        return null;
    }

    @Override
    public Tile askForTile(TileSelector selector) throws UndoException {
        // TODO
        return null;
    }

    @Override
    public Integer askForEffectChoice(List<Integer> choices) throws UndoException {
        // TODO
        return null;
    }

    @Override
    public PaymentReceipt askForPayment(Player player, PaymentInvoice payment, Integer effect) throws UndoException {
        // TODO
        return null;
    }


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
}
