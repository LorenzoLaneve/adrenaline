package it.polimi.deib.newdem.adrenaline.model.items;

import java.io.Serializable;
import java.util.Objects;

public class AmmoSet implements Serializable {

    private final int redAmmos;
    private final int yellowAmmos;
    private final int blueAmmos;

    /**
     * Initializes the AmmoSet object.
     * @param redAmmos The amount of starting red ammos in the set.
     * @param yellowAmmos The amount of starting yellow ammos in the set.
     * @param blueAmmos The amount of starting blue ammos in the set.
     * @throws IllegalArgumentException if any of the starting amounts of ammos is not between 0 and 3.
     */

    public AmmoSet(int redAmmos, int yellowAmmos, int blueAmmos) {

        boolean legalRedAmmos;
        boolean legalYellowAmmos;
        boolean legalBlueAmmos;

        legalRedAmmos = redAmmos <= 3 && redAmmos >= 0;
        legalYellowAmmos = yellowAmmos <= 3 && yellowAmmos >= 0;
        legalBlueAmmos = blueAmmos <= 3 && blueAmmos >= 0;

        if(legalRedAmmos && legalBlueAmmos && legalYellowAmmos){
            this.redAmmos = redAmmos;
            this.yellowAmmos = yellowAmmos;
            this.blueAmmos = blueAmmos;
        }
        else{
            throw new IllegalArgumentException("0-3 ammos of each color accepted");
        }


    }

    /**
     * @return amount of red ammos in the set.
     */

    public int getRedAmmos() {
        return redAmmos;
    }

    /**
     * @return amount of yellow ammos in the set.
     */
    public int getYellowAmmos() {
        return yellowAmmos;
    }

    /**
     * @return amount of blue ammos in the set.
     */
    public int getBlueAmmos() {
        return blueAmmos;
    }

    /**
     * @return total amount of ammos of any color.
     */
    public int getTotalAmmos() {
        return redAmmos + yellowAmmos + blueAmmos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(redAmmos, yellowAmmos, blueAmmos);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof AmmoSet)) {
            return false;
        }
        AmmoSet ammoSet = (AmmoSet) o;
        return redAmmos == ammoSet.getRedAmmos() &&
                yellowAmmos == ammoSet.getYellowAmmos() &&
                blueAmmos == ammoSet.getBlueAmmos();
    }

}
