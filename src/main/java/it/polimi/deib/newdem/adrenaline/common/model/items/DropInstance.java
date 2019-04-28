package it.polimi.deib.newdem.adrenaline.common.model.items;

import java.util.Objects;

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
     * @param o the drop to compare.
     * @return boolean value stating if the drops have the same ammos and both include or not powerUps.
     */
    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof DropInstance)) {
            return false;
        }

        DropInstance dropInstance = (DropInstance) o;

        return (this.ammoSet.equals(dropInstance.getAmmos()) &&
                this.hasPowerUp() == dropInstance.hasPowerUp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ammoSet, hasPowerUp);
    }

    /**Copies the drop instance.
     *
     * @return a new equal instance of DropInstance.
     */
    public DropInstance copyDrop(){
        return new DropInstance(this.ammoSet, this.hasPowerUp());
    }
}
