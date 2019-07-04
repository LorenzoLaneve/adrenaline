package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.io.Serializable;

/**
 * Represents a payment invoice containing a price for an action or card.
 */
public class PaymentInvoice implements Serializable {

    private int redAmmos;
    private int blueAmmos;
    private int yellowAmmos;
    private int anyAmmos;

    /**
     * Initializes the invoice with the given ammo prices.
     * @param anyAmmos The number of ammos that have to be paid without color requirement.
     */
    public PaymentInvoice(int redAmmos, int blueAmmos, int yellowAmmos, int anyAmmos) {
        this.redAmmos = redAmmos;
        this.blueAmmos = blueAmmos;
        this.yellowAmmos = yellowAmmos;
        this.anyAmmos = anyAmmos;
    }

    /**
     * Returns the number of red ammos that have to be paid.
     */
    public int getRedAmmos() {
        return redAmmos;
    }

    /**
     * Returns the number of blue ammos that have to be paid.
     */
    public int getBlueAmmos() {
        return blueAmmos;
    }

    /**
     * Returns the number of yellow ammos that have to be paid.
     */
    public int getYellowAmmos() {
        return yellowAmmos;
    }

    /**
     * Returns the number of ammos that have to be paid without.
     */
    public int getAnyAmmos() {
        return anyAmmos;
    }

    public boolean matches(PaymentReceipt receipt) {
        int pRedAmmos = receipt.getPayedRedAmmos();
        int pBlueAmmos = receipt.getPayedBlueAmmos();
        int pYellowAmmos = receipt.getPayedYellowAmmos();

        for (PowerUpCard card : receipt.getPayedPowerUpCards()) {
            switch (card.getEquivalentAmmo()) {
                case RED:
                    pRedAmmos++;
                    break;
                case BLUE:
                    pBlueAmmos++;
                    break;
                case YELLOW:
                    pYellowAmmos++;
                    break;
            }
        }

        return redAmmos == pRedAmmos && blueAmmos == pBlueAmmos && yellowAmmos == pYellowAmmos;
    }
}
