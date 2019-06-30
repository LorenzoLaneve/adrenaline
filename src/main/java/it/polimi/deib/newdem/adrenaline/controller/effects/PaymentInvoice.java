package it.polimi.deib.newdem.adrenaline.controller.effects;

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
}
