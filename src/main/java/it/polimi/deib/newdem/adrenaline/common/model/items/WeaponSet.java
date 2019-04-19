package it.polimi.deib.newdem.adrenaline.common.model.items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WeaponSet {

    private HashSet<WeaponCard> cards;

    /**Creates a new empty {@code WeaponSet}.
     *
     */
    public WeaponSet() {
        cards = new HashSet<>();
    }

    /**
     *
     * @return the between 0 and 3 weapons present in the set.
     */
    List<WeaponCard> getWeapons() {
        return new ArrayList<>(cards);
    }

    /**Adds a weapon to the set.
     *
     * @param weapon The weapon to add to the set.
     * @throws OutOfSlotsException when there are already 3 weapons present in the set.
     * @throws IllegalArgumentException when trying to add a weapon already present in the set.
     */
    void addWeapon(WeaponCard weapon) throws OutOfSlotsException {
        if(cards.size()<3){
            if(!cards.contains(weapon)){
                cards.add(weapon);
            }
            else {
                throw new IllegalArgumentException("Weapon already present in the set.");
            }
        }
        else {
            throw new OutOfSlotsException("The Weapon set is full.");
        }
    }

    /**Removes a weapon from the set.
     *
     * @param weapon The weapon to remove from the set.
     * @throws IllegalArgumentException if weapon is not present in the set.
     */
    void removeWeapon(WeaponCard weapon) {
        if(cards.contains(weapon)){
            cards.remove(weapon);
        }
        else throw new IllegalArgumentException("The weapon is not present in the set");

    }

}
