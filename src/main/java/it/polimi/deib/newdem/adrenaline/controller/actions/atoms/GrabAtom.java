package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.changes.PaymentGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerInventory;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponImpl;
import it.polimi.deib.newdem.adrenaline.model.map.NotOrdinaryTileException;
import it.polimi.deib.newdem.adrenaline.model.map.NotSpawnPointTileException;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

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
        try {
            WeaponCard cardToPickup = chooseWeapon(tile.inspectWeaponSet().getWeapons());
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
        } catch (Exception x) {
            // nothing to do here
        }
    }

    private WeaponCard chooseWeapon(List<WeaponCard> availableWeapons) {
        WeaponCard selectedWeapon = null;

        do{
            try{
                selectedWeapon = parent.getDataSource().chooseWeaponCard(availableWeapons);
            }
            catch (UndoException e) {
                // do nothing
            }
        }
        while (null == selectedWeapon);
        return selectedWeapon;
    }

}

