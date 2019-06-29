package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceiptData;
import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class TurnPaymentResponse implements UserEvent {

    private TurnView.ValOrUndo<PaymentReceiptData> receipt;

    public TurnPaymentResponse(TurnView.ValOrUndo<PaymentReceiptData> receipt) {
        this.receipt = receipt;
    }

    public TurnView.ValOrUndo<PaymentReceiptData> getValue() {
        return receipt;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}