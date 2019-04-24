package it.polimi.deib.newdem.adrenaline.common.model.items;

import it.polimi.deib.newdem.adrenaline.common.model.map.WeaponAlreadyPresentException;

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
    public List<WeaponCard> getWeapons() {
        return new ArrayList<>(cards);
    }

    /**Adds a weapon to the set.
     *
     * @param weapon The weapon to add to the set.
     * @throws OutOfSlotsException when there are already 3 weapons present in the set.
     * @throws IllegalArgumentException when trying to add a weapon already present in the set.
     */
    public void addWeapon(WeaponCard weapon) throws OutOfSlotsException, WeaponAlreadyPresentException {
        if(cards.size()<3){
            if(!cards.contains(weapon)){
                cards.add(weapon);
            }
            else {
                throw new WeaponAlreadyPresentException("Weapon already present in the set.");
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
    public void removeWeapon(WeaponCard weapon) {
        if(cards.contains(weapon)){
            cards.remove(weapon);
        }
        else throw new IllegalArgumentException("The weapon is not present in the set");

    }

    /**Compares the set to another weapon set.
     *
     * @param weaponSet the set to compare
     * @return boolean value stating if the sets include the same weapons.
     */
    public boolean equalWeaponSet(WeaponSet weaponSet){
        boolean equal = true;

        for(WeaponCard weaponCard:this.getWeapons()){
            if(!(weaponSet.getWeapons().contains(weaponCard))){
                equal = false;
            }
        }

        return equal;
    }
    /**Copies the weapon set.
     *
     * @return a new equal instance of weapon set.
     */
    public WeaponSet copyWeaponSet(){
        WeaponSet copySet = new WeaponSet();

        for(WeaponCard weaponCard:this.getWeapons()){
            try{
                copySet.addWeapon(weaponCard);
            }
            catch (OutOfSlotsException e){
                //TODO handel exception
            }
            catch (WeaponAlreadyPresentException e){
                //TODO handel exception
            }
        }

        return copySet;
    }

}
