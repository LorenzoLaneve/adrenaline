package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.items.Deck;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Object that describes the ammos and power ups a player has used to pay.
 */
public class PaymentReceipt {

    private List<PowerUpCard> powerUps;

    private int yellowAmmos;

    private int redAmmos;

    private int blueAmmos;

    /**
     * Initializes the receipt with the payed ammos and power ups.
     */
    public PaymentReceipt(int redAmmos, int blueAmmos, int yellowAmmos, List<PowerUpCard> powerUps) {
        this.redAmmos = redAmmos;
        this.blueAmmos = blueAmmos;
        this.yellowAmmos = yellowAmmos;

        this.powerUps = new ArrayList<>(powerUps);
    }

    /**
     * Constructs a PaymentReceipt object converting the integers into the corresponding power up cards
     * according to the given power up deck.
     */
    public static PaymentReceipt fromData(PaymentReceiptData receiptData, Deck<PowerUpCard> powerUpDeck) {
        List<PowerUpCard> powerUpCards = new ArrayList<>();

        for(Integer cardID : receiptData.getPowerUps()) {
            powerUpCards.add(powerUpDeck.getCardFromId(cardID));
        }

        return new PaymentReceipt(receiptData.getPayedRedAmmos(), receiptData.getPayedBlueAmmos(), receiptData.getPayedYellowAmmos(), powerUpCards);
    }


    /**
     * Returns the number of red ammos payed with this receipt.
     */
    public int getPayedRedAmmos() {
        return redAmmos;
    }

    /**
     * Returns the number of blue ammos payed with this receipt.
     */
    public int getPayedBlueAmmos() {
        return blueAmmos;
    }

    /**
     * Returns the number of yellow ammos payed with this receipt.
     */
    public int getPayedYellowAmmos() {
        return yellowAmmos;
    }

    /**
     * Returns the power up cards payed with this receipt.
     */
    public List<PowerUpCard> getPayedPowerUpCards() {
        return new ArrayList<>(powerUps);
    }

}
