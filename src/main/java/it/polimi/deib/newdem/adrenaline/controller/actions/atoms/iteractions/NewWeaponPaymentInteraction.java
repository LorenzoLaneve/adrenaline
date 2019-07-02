package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.PaymentGameChange;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public class NewWeaponPaymentInteraction extends InteractionBase {

    private WeaponCard newCard;
    private GameChange paymentGC;

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
