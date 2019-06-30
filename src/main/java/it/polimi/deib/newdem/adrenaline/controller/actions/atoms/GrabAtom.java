package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.PaymentGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerInventory;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponSet;
import it.polimi.deib.newdem.adrenaline.model.map.NotOrdinaryTileException;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;

import java.util.ArrayList;
import java.util.List;

public class GrabAtom extends AtomBase {

    public GrabAtom(AtomsContainer parent) {
        super(parent);
    }

    @Override
    public void execute() throws ConnectionException {
        // pickup from tile
        Tile tile = parent.getActor().getTile();
        if(tile.hasSpawnPoint()) {
            handleSpawn(tile);
        }
        else {
            // => tile has drop
            handleOrdinary(tile);
        }
    }

    private void handleOrdinary(Tile tile) {
        try {
            parent.getActor().getInventory().addDrop(tile.grabDrop());
        }
        catch (NotOrdinaryTileException e) {
            throw new IllegalStateException();
        }
    }

    private void handleSpawn(Tile tile) {
    // choose new weapon
    WeaponCard newCard = chooseWeapon(tile.inspectWeaponSet().getWeapons());
        // pay for it
        performPayment(newCard);
        if (parent.getActor().getInventory().getWeaponAmount() >= PlayerInventory.MAX_WEAPONS + 1) {
            // drop old weapon
            discardWeapon();
        }
    }

    private WeaponCard chooseWeapon(List<WeaponCard> availableweapons) {
        WeaponCard selectedWeapon = null;

        do{
            try{
                selectedWeapon = chooseWeaponUndoable(availableweapons);
            }
            catch (UndoException e) {
                // do nothing
            }
        }
        while (null == selectedWeapon);
        return selectedWeapon;
    }

    private WeaponCard chooseWeaponUndoable(List<WeaponCard> availableWeapons) throws UndoException {
        ArrayList<Integer> ids = new ArrayList<>();
        for(WeaponCard wc : availableWeapons) {
            ids.add(wc.getCardID());
        }
        int seelctedId = parent.getDataSource().actionDidRequestChoice(ids);

        for(WeaponCard wc : availableWeapons) {
            if(wc.getCardID() == seelctedId) return wc;
        }
        throw new IllegalStateException();
    }

    private void discardWeapon() {
        WeaponCard discardedCard = null;
        do {
            try {
                discardedCard = chooseWeaponUndoable(parent.getActor().getInventory().getAllWeaponCards());
                parent.getActor().getInventory().removeWeaponFromCard(discardedCard);
            } catch (UndoException e) {
                // do not undo, try again until success
            }
        } while (null == discardedCard);
    }

    private void performPayment(WeaponCard card) {
        PaymentReceipt receipt = null;
        do {
            try {
                receipt = parent.getDataSource().actionDidRequestPayment(card.getPickupPrice(), card.getCardID());
                GameChange paymentGameChange = new PaymentGameChange(parent.getActor(), receipt);
                paymentGameChange.update(parent.getGame());
            } catch (UndoException e) {
                // do not undo
            }
        }
        while (null == receipt);
    }
}
