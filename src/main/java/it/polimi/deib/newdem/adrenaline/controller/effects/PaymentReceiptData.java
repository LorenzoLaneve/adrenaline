package it.polimi.deib.newdem.adrenaline.controller.effects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaymentReceiptData implements Serializable {

    private List<Integer> powerUps;

    private int yellowAmmos;

    private int redAmmos;

    private int blueAmmos;

    public PaymentReceiptData(int redAmmos, int blueAmmos, int yellowAmmos, List<Integer> powerUpsIDs) {
        this.redAmmos = redAmmos;
        this.blueAmmos = blueAmmos;
        this.yellowAmmos = yellowAmmos;

        this.powerUps = powerUpsIDs;
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

    public List<Integer> getPowerUps() {
        return new ArrayList<>(powerUps);
    }

}
