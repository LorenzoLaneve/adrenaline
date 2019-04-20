package it.polimi.deib.newdem.adrenaline.common.model.items;

public class DropInstance {

    private final AmmoSet ammoSet;
    private final boolean hasPowerUp;

    /**
     * Initializes the immutable DropInstance object.
     * @param ammoSet The ammo set present in the drop which can contain 2 or 3 ammos.
     * @param hasPowerUp The boolean flag tht signals the presence of a power-up card in the drop.
     * @throws IllegalArgumentException if a power-up card is present and the ammo set does not contain 2 ammos
     * or if a power-up card is not present and the ammo set dos not contain 3 ammos.
     */
    public DropInstance(AmmoSet ammoSet, boolean hasPowerUp) {
        if(hasPowerUp){
            if(ammoSet.getTotalAmmos() == 2){
                this.ammoSet = ammoSet;
                this.hasPowerUp = hasPowerUp;
            }
            else{
                throw new IllegalArgumentException("The ammo set must contain 2 ammos if a power up card is present");
            }
        }
        else{
            if(ammoSet.getTotalAmmos() == 3){
                this.ammoSet = ammoSet;
                this.hasPowerUp = hasPowerUp;
            }
            else{
                throw new IllegalArgumentException("The ammo set must contain 3 ammos if a power up card is not present");
            }
        }
    }

    /**
     * @return the drop's ammo set object.
     */
    public AmmoSet getAmmos() {
        return this.ammoSet;
    }

    /**
     * @return the drop's boolean flag to signal the presence of a power-up card.
     */
    public boolean hasPowerUp() {
        return hasPowerUp;
    }

    /**Compares the drop to another dropInstance.
     *
     * @param dropInstance the drop to compare.
     * @return boolean value stating if the drops have the same ammos and both include or not powerUps.
     */
    public boolean equalDrop(DropInstance dropInstance){
        boolean equal;

        equal = (this.ammoSet.getRedAmmos() == dropInstance.ammoSet.getRedAmmos() &&
                this.ammoSet.getBlueAmmos() == dropInstance.ammoSet.getBlueAmmos() &&
                this.ammoSet.getYellowAmmos() == dropInstance.ammoSet.getYellowAmmos() &&
                this.hasPowerUp() == dropInstance.hasPowerUp());

        return equal;
    }

    /**Copies the drop instance.
     *
     * @return a new equal instance of DropInstance.
     */
    public DropInstance copyDrop(){
        return new DropInstance(this.ammoSet, this.hasPowerUp());
    }
}
