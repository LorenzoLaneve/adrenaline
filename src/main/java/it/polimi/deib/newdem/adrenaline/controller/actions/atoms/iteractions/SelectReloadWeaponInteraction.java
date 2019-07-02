package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

import java.util.List;

public class SelectReloadWeaponInteraction extends InteractionBase {

    public SelectReloadWeaponInteraction(InteractionContext context) {
        super(context);
    }

    @Override
    public void execute() throws UndoException {
        Player player = context.getActor();

        List<WeaponCard> selectables = player.getInventory().getUnloadedWeapons().getWeapons();

        if(!selectables.isEmpty()) {
            WeaponCard selectedWeaponCard = context.getDataSource().chooseWeaponCard(selectables);

            context.pushInteraction(new ReloadPaymentInteraction(context, selectedWeaponCard));
        }
        /*
        * PlayerInventory inventory = parent.getActor().getInventory();
        try {

            while (!inventory.getDischargedWeapons().isEmpty()) {
                List<WeaponCard> selectables = inventory.getUnloadedWeapons().getWeapons();

                WeaponCard selectedWeaponCard = parent.getDataSource().chooseWeaponCard(selectables);
                if (selectedWeaponCard == null) break;

                PaymentReceipt receipt = parent.getDataSource().requestPayment(
                                selectedWeaponCard.getPickupPrice(),
                                selectedWeaponCard.getCardID()
                );

                applyGameChange(new PaymentGameChange(getActor(), receipt));

                for(Weapon weapon : inventory.getDischargedWeapons()) {
                    if(weapon.getCard().equals(selectedWeaponCard)) {
                        weapon.reload();
                    }
                }
                // ^ this is important
            }
        }
        catch (UndoException e) {
            // nothing to do here.
        }
        * */
    }

    @Override
    public void revert() {
        // nothing to revert
    }

    @Override
    public boolean requiresInput() {
        return true;
    }
}
