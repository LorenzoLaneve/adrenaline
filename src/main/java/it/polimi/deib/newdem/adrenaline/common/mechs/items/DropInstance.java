package it.polimi.deib.newdem.adrenaline.common.mechs.items;

public class DropInstance {

    final AmmoSet ammoSet;
    final PowerUpCard powerUpCard;

    public DropInstance(AmmoSet ammoSet, PowerUpCard powerUpCard) {
        this.ammoSet = ammoSet;
        this.powerUpCard = powerUpCard;
    }

    AmmoSet getAmmos() {
        // TODO implement
        return this.ammoSet;
    }

    PowerUpCard getPowerUp() {
        // TODO implement
        return null;
    }

    boolean hasPowerUp() {
        // TODO implement
        return false;
    }
}
