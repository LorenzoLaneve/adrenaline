package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.AbortedException;
import it.polimi.deib.newdem.adrenaline.controller.TimedExecutor;
import it.polimi.deib.newdem.adrenaline.controller.TimeoutException;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public abstract class AtomContext extends AtomBase implements EffectContext {

    protected WeaponCard usedWeapon;

    public AtomContext(AtomsContainer parent) {
        super(parent);
    }

    @Override
    public void applyGameChange(GameChange gameChange) {
        gameChange.update(parent.getGame());
    }

    @Override
    public void revertGameChange(GameChange gameChange) {
        gameChange.revert(parent.getGame());
    }

    @Override
    public Player getActor() {
        return parent.getActor();
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
        return parent.getDataSource().requestPlayer(player, selector, forceChoice);
    }

    @Override
    public Tile chooseTile(TileSelector selector, boolean forceChoice) throws UndoException {
        return parent.getDataSource().requestTile(selector, forceChoice);
    }

    @Override
    public Integer chooseFragment(List<Integer> choices) throws UndoException {
        int cardID = -1;
        if (usedWeapon != null) cardID = usedWeapon.getCardID();

        return parent.getDataSource().requestFragment(cardID, choices, false);
    }

    @Override
    public PaymentReceipt choosePayment(PaymentInvoice invoice, int choice) throws UndoException {
        return parent.getDataSource().requestPayment(invoice, choice);
    }

    @Override
    public void damageDealtTrigger(Player attacker, Player victim) {
        // TODO
    }

    @Override
    public void damageTakenTrigger(Player attacker, Player victim) {
        TimedExecutor.pauseTimer();

        TimedExecutor revengeExecutor = new TimedExecutor(this::revenge);
        try {
            revengeExecutor.execute(15);
        } catch (TimeoutException | AbortedException e) {
            // nothing to do maybe.
        } finally {
            TimedExecutor.resumeTimer();
        }
    }

    private void revenge() {
        // TODO
    }

}
