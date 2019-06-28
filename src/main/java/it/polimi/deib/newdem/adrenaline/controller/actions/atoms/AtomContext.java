package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public abstract class AtomContext extends AtomBase implements EffectContext {

    public AtomContext(AtomsContainer parent) {
        super(parent);
    }

    @Override
    public void applyGameChange(GameChange gameChange) {

    }

    @Override
    public void revertGameChange(GameChange gameChange) {

    }

    @Override
    public Player getActor() {
        return getActor();
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
        Player selectedPlayer;

        do {
            selectedPlayer = parent.getDataSource().actionDidRequestPlayer(
                    player, selector
            );
        }
        while ((null == selectedPlayer) && forceChoice);
        return selectedPlayer;
    }

    @Override
    public Tile chooseTile(TileSelector selector, boolean forceChoice) throws UndoException {
        Tile selectedTile;

        do {
            selectedTile = parent.getDataSource().actionDidRequestTile(selector);
        }
        while ((null == selectedTile) && forceChoice);

        return selectedTile;
    }

    @Override
    public Integer chooseFragment(List<Integer> choices) throws UndoException {
        return parent.getDataSource().actionDidRequestChoice(choices);
    }

    @Override
    public PaymentReceipt choosePayment(PaymentInvoice invoice, int choice) throws UndoException {
        return parent.getDataSource().actionDidRequestPayment(invoice, choice);
    }

    @Override
    public void damageDealtTrigger(Player attacker, Player victim) {

    }

    @Override
    public void damageTakenTrigger(Player attacker, Player victim) {

    }
}
