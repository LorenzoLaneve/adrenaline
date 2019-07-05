package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.PaymentGameChange;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
/**
 * Interaction encapsulating the payment for a new weapon within a GRAB interaction
 */
public class NewWeaponPaymentInteraction extends InteractionBase {

    private WeaponCard newCard;
    private GameChange paymentGC;

    /**
     * Builds a new {@code ReloadPaymentInteraction } bound to the given {@code InteractionContext}
     * @param context this interaction's environment
     * @param newCard selected weapon to pay for
     */
    public NewWeaponPaymentInteraction(InteractionContext context, WeaponCard newCard) {
        super(context);
        this.newCard = newCard;
        paymentGC = null;
    }

    @Override
    public void execute() throws UndoException {
        PaymentReceipt receipt = context.getDataSource().requestPayment(
                newCard.getPickupPrice(),
                newCard.getCardID());

        if (receipt == null)
            throw new UndoException();

        paymentGC = new PaymentGameChange(context.getActor(), receipt);
        paymentGC.update(context.getGame());

        context.pushInteraction(new RequireDiscardInteraction(context, newCard));
    }

    @Override
    public void revert() {
        // revert paymentGC
        paymentGC.revert(context.getGame());
    }

    @Override
    public boolean requiresInput() {
        return true;
    }
}
