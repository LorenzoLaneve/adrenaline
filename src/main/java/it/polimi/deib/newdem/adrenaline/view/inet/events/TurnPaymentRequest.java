package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.ArrayList;
import java.util.List;

public class TurnPaymentRequest implements UserEvent {

    private PaymentInvoice invoice;
    private AmmoSet playerInv;
    private ArrayList<Integer> cardIDs;
    private int fragmentToPay;

    public TurnPaymentRequest(PaymentInvoice invoice, AmmoSet playerInv, List<Integer> cardIDs, int fragmentToPay) {
        this.invoice = invoice;
        this.playerInv = playerInv;
        this.cardIDs = new ArrayList<>(cardIDs);
        this.fragmentToPay = fragmentToPay;
    }

    public PaymentInvoice getInvoice() {
        return invoice;
    }

    public AmmoSet getPlayerAmmos() {
        return playerInv;
    }

    public List<Integer> getAvailablePowerUps() {
        return cardIDs;
    }

    public int getFragmentToPay() {
        return fragmentToPay;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
