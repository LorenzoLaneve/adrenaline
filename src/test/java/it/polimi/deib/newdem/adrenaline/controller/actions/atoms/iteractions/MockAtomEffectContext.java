package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomEffectContext;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

/**
 * Mock object for testing purposes
 *
 * All of its methods do nothing
 */
public class MockAtomEffectContext implements AtomEffectContext {


    @Override
    public void setSelectedWeaponCard(WeaponCard card) {

    }

    @Override
    public void setEnableDamageTriggers(boolean flag) {

    }

    @Override
    public void setVictim(Player victim) {

    }

    @Override
    public List<Player> getDamagedPlayers() {
        return null;
    }

    @Override
    public void applyGameChange(GameChange gameChange) {

    }

    @Override
    public void revertGameChange(GameChange gameChange) {

    }

    @Override
    public Player getActor() {
        return null;
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
    public PaymentReceipt choosePayment(PaymentInvoice price, int choice) throws UndoException {
        return null;
    }

    @Override
    public void damageDealtTrigger(Player attacker, Player victim) {

    }

    @Override
    public void damageTakenTrigger(Player attacker, Player victim) {

    }
}
