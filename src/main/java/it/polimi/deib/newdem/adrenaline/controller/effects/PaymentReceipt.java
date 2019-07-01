package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.items.Deck;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaymentReceipt implements Serializable {

    private List<PowerUpCard> powerUps;

    private int yellowAmmos;

    private int redAmmos;

    private int blueAmmos;

    public PaymentReceipt(int redAmmos, int blueAmmos, int yellowAmmos, List<PowerUpCard> powerUps) {
        this.redAmmos = redAmmos;
        this.blueAmmos = blueAmmos;
        this.yellowAmmos = yellowAmmos;

        this.powerUps = new ArrayList<>(powerUps);
    }

    public static PaymentReceipt fromData(PaymentReceiptData receiptData, Deck<PowerUpCard> powerUpDeck) {
        List<PowerUpCard> powerUpCards = new ArrayList<>();

        for(Integer cardID : receiptData.getPowerUps()) {
            powerUpCards.add(powerUpDeck.getCardFromId(cardID));
        }

        return new PaymentReceipt(receiptData.getPayedRedAmmos(), receiptData.getPayedBlueAmmos(), receiptData.getPayedYellowAmmos(), powerUpCards);
    }




    public int getPayedRedAmmos() {
        return redAmmos;
    }

    public int getPayedBlueAmmos() {
        return blueAmmos;
    }

    public int getPayedYellowAmmos() {
        return yellowAmmos;
    }

    public List<PowerUpCard> getPayedPowerUpCards() {
        return new ArrayList<>(powerUps);
    }

}
