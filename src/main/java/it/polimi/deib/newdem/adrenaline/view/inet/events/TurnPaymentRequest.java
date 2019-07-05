package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * The server is requesting the user to choose a way to pay the given payment invoice.
 * The resources of the player, including ammos and power ups that can be sued to pay, are passed.
 * {@code fragmentToPay} is an ID that explains what the user is going to pay.
 * @see UserEvent to see what this class is used for.
 */
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
