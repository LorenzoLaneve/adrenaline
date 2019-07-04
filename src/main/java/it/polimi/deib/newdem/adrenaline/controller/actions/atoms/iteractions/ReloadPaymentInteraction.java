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
