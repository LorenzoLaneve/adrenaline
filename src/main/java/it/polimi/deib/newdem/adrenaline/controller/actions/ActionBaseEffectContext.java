package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public class ActionBaseEffectContext extends ActionBasePlain implements EffectContext {

    public ActionBaseEffectContext(Player actor, ActionDataSource actionDataSource, Game game) {
        super(actor, actionDataSource, game);
    }

    @Override
    public void applyGameChange(GameChange gameChange) {

    }

    @Override
    public void revertGameChange(GameChange gameChange) {

    }

    @Override
    public Player getAttacker() {
        return null;
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
