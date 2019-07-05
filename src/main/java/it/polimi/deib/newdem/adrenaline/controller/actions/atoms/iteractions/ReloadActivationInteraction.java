package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerInventory;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

/**
 * Interaction encapsulating the final application of a RELOAD atom.
 *
 * In here a weapon will switch from unloaded to loaded and any ammo used to to do
 * will be remoed from te player's inventory
 */
public class ReloadActivationInteraction extends InteractionBase {

    private WeaponCard selectedWeapon;


    public ReloadActivationInteraction(InteractionContext context, WeaponCard selectedWeapon) {
        super(context);
        this.selectedWeapon = selectedWeapon;
    }

    @Override
    public void execute() throws UndoException {
        PlayerInventory inventory = context.getActor().getInventory();
        for(Weapon weapon : inventory.getDischargedWeapons()) {
            if(weapon.getCard().equals(selectedWeapon)) {
                weapon.reload();

                if(!inventory.getDischargedWeapons().isEmpty()) {
                    context.pushInteraction(new SelectReloadWeaponInteraction(context));
                }
            }
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

    }

    @Override
    public boolean requiresInput() {
        return false;
    }
}
