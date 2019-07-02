package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.PaymentGameChange;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public class ReloadPaymentInteraction extends InteractionBase {

    private WeaponCard selectedWeapon;
    private GameChange paymentGC;

    public ReloadPaymentInteraction(InteractionContext context, WeaponCard selectedWeapon) {
        super(context);
        this.selectedWeapon = selectedWeapon;
    }

    @Override
    public void execute() throws UndoException {
        PaymentReceipt receipt = context.getDataSource().requestPayment(
                selectedWeapon.getReloadPrice(),
                selectedWeapon.getCardID()
        );

        if(null == receipt) { throw new UndoException(); }

        paymentGC = new PaymentGameChange(context.getActor(), receipt);
        paymentGC.update(context.getGame());

        context.pushInteraction(new ReloadActivationInteraction(context, selectedWeapon));
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
        paymentGC.revert(context.getGame());
    }

    @Override
    public boolean requiresInput() {
        return true;
    }
}
