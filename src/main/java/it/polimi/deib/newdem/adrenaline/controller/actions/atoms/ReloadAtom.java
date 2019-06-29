package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.changes.PaymentGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerInventory;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;

import java.util.List;
import java.util.stream.Collectors;

public class ReloadAtom extends AtomContext {

    public ReloadAtom(AtomsContainer parent) {
        super(parent);
    }

    @Override
    public void execute() throws ConnectionException {
        PlayerInventory inventory = parent.getActor().getInventory();
        try {

            while (!inventory.getUnloadedWeapons().getWeapons().isEmpty()) {
                List<Integer> ids = inventory
                        .getUnloadedWeapons()
                        .getWeapons()
                        .stream()
                        .map(WeaponCard::getCardID)
                        .collect(Collectors.toList());

                int selectedId = parent.getDataSource().actionDidRequestChoice(ids);

                WeaponCard selectedWeaponCard = null;
                for(WeaponCard w : inventory.getUnloadedWeapons().getWeapons()) {
                    if(w.getCardID() == selectedId) {
                        selectedWeaponCard = w;
                    }
                }

                if(null == selectedWeaponCard) throw new IllegalStateException();

                PaymentReceipt receipt = parent
                        .getDataSource()
                        .actionDidRequestPayment(
                                selectedWeaponCard.getPickupPrice(),
                                selectedWeaponCard.getCardID());

                applyGameChange(new PaymentGameChange(getActor(), receipt));
            }
        }
        catch(UndoException e) {
                // exit while loop
        }
    }
}
