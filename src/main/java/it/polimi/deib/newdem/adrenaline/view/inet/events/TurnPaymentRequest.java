package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class TurnPaymentRequest implements UserEvent {

    private PaymentInvoice invoice;
    private int fragmentToPay;

    public TurnPaymentRequest(PaymentInvoice invoice, int fragmentToPay) {
        this.invoice = invoice;
        this.fragmentToPay = fragmentToPay;
    }

    public PaymentInvoice getInvoice() {
        return invoice;
    }

    public int getFragmentToPay() {
        return fragmentToPay;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
