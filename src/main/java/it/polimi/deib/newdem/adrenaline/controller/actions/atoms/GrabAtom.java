package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.changes.PaymentGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerInventory;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponImpl;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponImpl;
import it.polimi.deib.newdem.adrenaline.model.map.NotOrdinaryTileException;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;


public class GrabAtom extends AtomBase {

    public GrabAtom(AtomsContainer parent) {
        super(parent);
    }

    @Override
    public void execute() throws UndoException {
        Tile tile = parent.getActor().getTile();
        if(tile.hasSpawnPoint()) {
            handleSpawn(tile);
        }  else {
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

    private void handleSpawn(Tile tile) throws UndoException {
    // choose new weapon
    WeaponCard newCard = chooseWeapon(tile.inspectWeaponSet().getWeapons());
        // pay for it
        performPayment(newCard);
        if (parent.getActor().getInventory().getWeaponAmount() >= PlayerInventory.MAX_WEAPONS) {
            // drop old weapon
            discardWeapon();
        }
        parent.getActor().getInventory().addWeapon(new WeaponImpl(newCard, parent.getActor()));
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
        return parent.getDataSource().chooseWeaponCard(availableWeapons);
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

    // what does this do?
    private void doWhatNow(Tile tile) {
        try {
            WeaponCard cardToPickup = parent.getDataSource().chooseWeaponCard(tile.inspectWeaponSet().getWeapons());
            if (cardToPickup != null) {
                PaymentReceipt receipt = parent.getDataSource().requestPayment(cardToPickup.getPickupPrice(), cardToPickup.getCardID());

                WeaponCard weaponToSwap = null;
                if (parent.getActor().getInventory().getWeaponAmount() >= PlayerInventory.MAX_WEAPONS) {
                    weaponToSwap = parent.getDataSource().chooseWeaponCard(tile.inspectWeaponSet().getWeapons());
                    if (weaponToSwap != null) {
                        parent.getActor().getInventory().removeWeaponFromCard(weaponToSwap);
                    }
                }

                if (parent.getActor().getInventory().getWeaponAmount() < PlayerInventory.MAX_WEAPONS) {
                    tile.grabWeapon(cardToPickup);

                    parent.getActor().getInventory().addWeapon(new WeaponImpl(cardToPickup, parent.getActor()));

                    if (weaponToSwap != null) {
                        tile.addWeapon(weaponToSwap);
                    }

                    new PaymentGameChange(parent.getActor(), receipt).update(parent.getGame());
                }

            }
        } catch (Exception x) {
            // nothing to do here
        }

    }

    private void performPayment(WeaponCard card) throws UndoException {
        parent.getDataSource().requestPayment(card.getPickupPrice(), card.getCardID());
    }
}

