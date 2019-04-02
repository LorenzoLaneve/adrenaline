package it.polimi.deib.newdem.adrenaline.common.mechs.items;

public class DropInstance {

    private final AmmoSet ammoSet;
    private final PowerUpCard powerUpCard;

    public DropInstance(AmmoSet ammoSet, PowerUpCard powerUpCard) {
        this.ammoSet = ammoSet;
        this.powerUpCard = powerUpCard;
        // TODO implement
    }

    AmmoSet getAmmos() {
        // TODO implement
        return this.ammoSet;
    }

    PowerUpCard getPowerUp() {
        // TODO implement
        return powerUpCard;
    }

    boolean hasPowerUp() {
        // TODO implement
        return false;
    }
}
