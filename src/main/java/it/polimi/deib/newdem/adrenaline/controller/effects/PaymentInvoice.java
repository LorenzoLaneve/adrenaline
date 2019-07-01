package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.io.Serializable;

public class PaymentInvoice implements Serializable {

    int redAmmos;
    int blueAmmos;
    int yellowAmmos;
    int anyAmmos;

    public PaymentInvoice(int redAmmos, int blueAmmos, int yellowAmmos, int anyAmmos) {
        this.redAmmos = redAmmos;
        this.blueAmmos = blueAmmos;
        this.yellowAmmos = yellowAmmos;
        this.anyAmmos = anyAmmos;
    }

    public int getRedAmmos() {
        return redAmmos;
    }

    public int getBlueAmmos() {
        return blueAmmos;
    }

    public int getYellowAmmos() {
        return yellowAmmos;
    }

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
