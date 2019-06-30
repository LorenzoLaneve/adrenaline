package it.polimi.deib.newdem.adrenaline.controller.effects;

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
