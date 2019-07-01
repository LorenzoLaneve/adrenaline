package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.changes.PaymentGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerInventory;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

import java.util.List;

public class ReloadAtom extends AtomContext {

    public ReloadAtom(AtomsContainer parent) {
        super(parent);
    }

    @Override
    public void execute() {
        PlayerInventory inventory = parent.getActor().getInventory();
        try {

            while (!inventory.getUnloadedWeapons().getWeapons().isEmpty()) {
                List<WeaponCard> selectables = inventory.getUnloadedWeapons().getWeapons();

                WeaponCard selectedWeaponCard = parent.getDataSource().chooseWeaponCard(selectables);
                if (selectedWeaponCard == null) break;

                PaymentReceipt receipt = parent.getDataSource().requestPayment(
                                selectedWeaponCard.getPickupPrice(),
                                selectedWeaponCard.getCardID()
                );

                applyGameChange(new PaymentGameChange(getActor(), receipt));
            }
        }
        catch (UndoException e) {
            // nothing to do here.
        }
    }
}
