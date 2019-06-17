package it.polimi.deib.newdem.adrenaline.model.items;

public class DropCardImpl implements DropCard{

    private final AmmoSet ammoSet;
    private final boolean hasPowerUp;
    private final int cardID;

    public DropCardImpl(int id,AmmoSet ammoSet, boolean hasPowerUp){
        this.ammoSet = ammoSet;
        this.hasPowerUp = hasPowerUp;
        this.cardID = id;
    }

    public AmmoSet getAmmoSet() {
        return new AmmoSet(ammoSet.getRedAmmos(), ammoSet.getYellowAmmos(), ammoSet.getBlueAmmos());
    }

    public boolean hasPowerUp() {
        return hasPowerUp;
    }

    @Override
    public int getID() {
        return cardID;
    }
}
