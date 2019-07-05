package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.items.*;
import it.polimi.deib.newdem.adrenaline.model.map.WeaponAlreadyPresentException;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import static it.polimi.deib.newdem.adrenaline.model.items.AmmoColor.BLUE;
import static it.polimi.deib.newdem.adrenaline.model.items.AmmoColor.RED;
import static it.polimi.deib.newdem.adrenaline.model.items.AmmoColor.YELLOW;
import static java.lang.Integer.min;
import static java.lang.Math.max;

/**
 * Object containing all the resources a player can have in an Adrenaline game,
 * including ammos, power up cards, and weapon cards.
 */
public class PlayerInventory {

    private Player player;
    private List<PowerUpCard> powerUpCards;
    private List<Weapon> weapons;


    public static final int INITIAL_AMMOS = 1;

    /**
     * Maximum amount of equipments for an inventory at any given time
     */
    public static final int MAX_EQUIPMENT = 3;

    /**
     * Maximum amount of weapons for an inventory at any given time
     */
    public static final int MAX_WEAPONS = 3;

    /**
     * Maximum amount of ammo cubes of any one color for an inventory at any given time
     */
    public static final int MAX_AMMO_PER_COLOR = 3;


    private EnumMap<AmmoColor, Integer> ammos;

    /**
     * Creates a new empty inventory for {@code Player}
     *
     * @param player the player this inventory belongs to. Not null.
     */
    public PlayerInventory(Player player) {
        if(null == player) throw new IllegalArgumentException();
        this.player = player;
        powerUpCards = new ArrayList<>(MAX_EQUIPMENT);
        weapons = new ArrayList<>(MAX_WEAPONS);
        ammos = new EnumMap<>(AmmoColor.class);
        ammos.put(RED, INITIAL_AMMOS);
        ammos.put(BLUE, INITIAL_AMMOS);
        ammos.put(YELLOW, INITIAL_AMMOS);
    }

    /**
     * Get a reference to the owner of this inventory as a WeaponSet
     *
     * @return owner
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * Get a {@code WeaponSet} containing the loaded weapons
     * @return loaded weapons
     */
    public WeaponSet getReadyWeapons(){
         WeaponSet ws = new WeaponSet();
         for(Weapon w : weapons) {
             try {
                 if(w.isLoaded()) {
                     ws.addWeapon(w.getCard());
                 }
             }
             catch (OutOfSlotsException | WeaponAlreadyPresentException e)
             {
                 throw new IllegalStateException();
             }
         }
         return ws;
    }

    /**
     * Return the Weapon objects of the loaded weapons
     */
    public List<Weapon> getLoadedWeapons() {
        ArrayList<Weapon> ready = new ArrayList<>();
        for(Weapon w : weapons) {
            if (w.isLoaded()) {
                ready.add(w);
            }
        }
        return ready;
    }

    /**
     * Get a {@code WeaponSet} containing the unloaded weapons
     * @return unloaded weapons
     */
    public WeaponSet getUnloadedWeapons(){
        WeaponSet ws = new WeaponSet();
        for(Weapon w : weapons) {
            try {
                if(!w.isLoaded()) {
                    ws.addWeapon(w.getCard());
                }
            }
            catch (OutOfSlotsException | WeaponAlreadyPresentException e)
            {
                throw new IllegalStateException();
            }
        }
        return ws;
    }

    /**
     * Get all the powerups currently in the inventory
     *
     * @return powerups
     */
    public List<PowerUpCard> getAllPowerUps(){
        List<PowerUpCard> cards = new ArrayList<>();
        for(PowerUpCard p : powerUpCards) {
            cards.add(p);
        }
        return new ArrayList<>(cards);
    }

    /**
     * Retrieves the amount od red ammo cubes currently in this inventory.
     *
     * @return amount of red ammo cubes
     */
    public int getRed() {
        return ammos.get(RED);
    }

    /**
     * Retrieves the amount od blue ammo cubes currently in this inventory.
     *
     * @return amount of blue ammo cubes
     */
    public int getBlue() {
        return ammos.get(BLUE);
    }

    /**
     * Retrieves the amount od yellow ammo cubes currently in this inventory.
     *
     * @return amount of yellow ammo cubes
     */
    public int getYellow() {
        return ammos.get(YELLOW);
    }

    /**
     * Adds {@code Amount} ammos of the given {@code AmmoColor} to this inventory.
     *
     * If there is already the maximum amount of ammos for any given color, that amount doesn't change.
     *
     * @param color color of the new ammos
     * @param amount amount of the new ammos. Not negative.
     */
    public void addAmmo(AmmoColor color, int amount) {
        if(amount < 0) throw new IllegalArgumentException();
        int delta = ammos.get(color);
        ammos.put(color, min(ammos.get(color) + amount, MAX_AMMO_PER_COLOR));
        delta = ammos.get(color) - delta;

        AmmoSet ammoSet = new AmmoSet(0,0,0);

        switch (color){
            case RED:
                ammoSet = new AmmoSet(delta, 0, 0);
                break;
            case BLUE:
                ammoSet = new AmmoSet(0, 0, delta);
                break;
            case YELLOW:
                ammoSet = new AmmoSet(0, delta, 0);
                break;
        }

        player.getListener().playerDidReceiveAmmos(player, ammoSet);
     }

    /**
     * Removes {@code amount} ammos of the given {@code color} form this inventory and
     * notifies the listener of said deduction.
     *
     * @param color of ammos to remove
     * @param amount of ammos to remove
     */
     public void removeAmmo(AmmoColor color, int amount){
        if(amount < 0) throw new IllegalArgumentException();
        int delta = ammos.get(color);
        ammos.put(color, min(ammos.get(color) - amount, MAX_AMMO_PER_COLOR));
        delta -= ammos.get(color);

        AmmoSet ammoSet = new AmmoSet(0,0,0);

        switch (color){
            case RED:
                ammoSet = new AmmoSet(delta, 0, 0);
                break;
            case BLUE:
                ammoSet = new AmmoSet(0, 0, delta);
                break;
            case YELLOW:
                ammoSet = new AmmoSet(0, delta, 0);
                break;
        }

        player.getListener().playerDidDiscardAmmos(player, ammoSet);
    }



    /**
     * Adds a {@code Weapon} to this inventory.
     *
     * @param weapon not null
     */
    public void addWeapon(Weapon weapon) {
        if (null == weapon) throw new IllegalArgumentException();
        if(weapons.size() < MAX_WEAPONS){
            weapons.add(weapon);
            player.getListener().playerDidReceiveWeaponCard(player, weapon.getCard());
        } else throw new IllegalStateException();
    }

    /**
     * Removes a given weapon from this inventory. Could also be a WeaponCard.
     * @param weapon not null
     */
    public void removeWeapon(Weapon weapon) {
        if(null == weapon) throw new IllegalArgumentException();

        if (weapons.contains(weapon)) {
            weapons.remove(weapon);
            player.getListener().playerDidDiscardWeaponCard(player, weapon.getCard());
        }
    }

    /**
     * Checks whether this inventory can accept a new powerup
     *
     * @return can this inventory accept a new powerup
     */
    public boolean canAcceptPowerUp() {
        return powerUpCards.size() < MAX_EQUIPMENT;
    }

    public void addPowerUp(PowerUpCard card) throws OutOfSlotsException {
        if(null == card) throw new IllegalArgumentException();
        if(!canAcceptPowerUp()) throw new OutOfSlotsException();
        powerUpCards.add(card);
        player.getListener().playerDidReceivePowerUpCard(player, card);
    }

    /**
     * Removes the given {@code PowerUpCard} from this inventory, if it is present (optional operation).
     * @param card Powerup to be removed from this inventory, if present
     */
    public void removePowerUp(PowerUpCard card) {
        powerUpCards.remove(card);
        player.getListener().playerDidDiscardPowerUpCard(player, card);
    }

    /**
     * Removes from this inventory all elements of the given {@code PowerUpCard}
     * that are present in the inventory (optional operation).
     *
     * @param powerUpCardList Powerups to be removed from this inventory, if present
     */
    public void removePowerUps(List<PowerUpCard> powerUpCardList){
        powerUpCards.removeAll(powerUpCardList);
        for(PowerUpCard card: powerUpCardList){
            player.getListener().playerDidDiscardPowerUpCard(player, card);
        }
    }

    /**
     * Retrieves a list of all the weapon cards of this inventory, regardless
     * of their corresponding {@code Weapon}'s state of loaded or discharged.
     *
     * @return cards of all the weapons in this inventory
     */
    public List<WeaponCard> getAllWeaponCards() {
        return weapons.stream().map(Weapon::getCard).collect(Collectors.toList());
    }

    /**
     * Adds all the ammos of the given {@code AmmoSet} to this inventory.
     * Excesses are truncated at {@code MAX_AMMO_PER_COLOR}
     * @param ammoSet ammos to add
     */
    public void addAmmoSet(AmmoSet ammoSet) {
        int deltaRed = ammos.get(RED);
        int deltaBlue = ammos.get(BLUE);
        int deltaYellow = ammos.get(YELLOW);

        ammos.put(RED, min(ammos.get(RED) + ammoSet.getRedAmmos(), MAX_AMMO_PER_COLOR));
        ammos.put(BLUE, min(ammos.get(BLUE) + ammoSet.getBlueAmmos(), MAX_AMMO_PER_COLOR));
        ammos.put(YELLOW, min(ammos.get(YELLOW) + ammoSet.getYellowAmmos(), MAX_AMMO_PER_COLOR));

        deltaRed = ammos.get(RED) - deltaRed;
        deltaBlue = ammos.get(BLUE) - deltaBlue;
        deltaYellow = ammos.get(YELLOW) - deltaYellow;

        player.getListener().playerDidReceiveAmmos(player, new AmmoSet(deltaRed, deltaYellow, deltaBlue));
    }

    /**
     * Removes all the ammos of the given {@code AmmoSet} to this inventory.
     *
     * @param ammoSet ammos to remove
     */
    public void removeAmmoSet(AmmoSet ammoSet) {
        int deltaRed = ammos.get(RED);
        int deltaBlue = ammos.get(BLUE);
        int deltaYellow = ammos.get(YELLOW);

        ammos.put(RED, max(ammos.get(RED) - ammoSet.getRedAmmos(), 0));
        ammos.put(BLUE, max(ammos.get(BLUE) - ammoSet.getBlueAmmos(), 0));
        ammos.put(YELLOW, max(ammos.get(YELLOW) - ammoSet.getYellowAmmos(), 0));

        deltaRed -= ammos.get(RED);
        deltaBlue -= ammos.get(BLUE);
        deltaYellow -= ammos.get(YELLOW);

        player.getListener().playerDidDiscardAmmos(player, new AmmoSet(deltaRed, deltaYellow, deltaBlue));
    }

    /**
     * Retrieves all the ammos in this inventory as an {@code AmmoSet}
     *
     * @return ammos in this inventory
     */
    public AmmoSet getAmmoSet() {
        return new AmmoSet(ammos.get(RED), ammos.get(YELLOW), ammos.get(BLUE));
    }

    /**
     * Retrieves all discharged weapons in this inventory
     *
     * @return discharged weapons
     */
    public List<Weapon> getDischargedWeapons() {
        ArrayList<Weapon> dischargedWeapons = new ArrayList<>();
        for(Weapon w : weapons) {
            if(!w.isLoaded()) {
                dischargedWeapons.add(w);
            }
        }
        return dischargedWeapons;
    }

    /**
     * Returns a list of all the {@code Weapon}s in this inventory.
     *
     * @return all the weapons
     */
    public List<Weapon> getAllWeapons() {
        return new ArrayList<>(weapons);
    }

    public List<PowerUpCard> getPowerUpByTrigger(PowerUpTrigger trigger) {
            return getAllPowerUps()
                    .stream()
                    .filter(card -> card.getTrigger() == trigger)
                    .collect(Collectors.toList());
    }

    /**
     * Checks if this inventory has sufficient resources
     * to pay for the given ammoSet
     * @param invoice
     * @return can this inventory pay the given ammoSet
     */
    public boolean canPay(PaymentInvoice invoice) {

        int availableRed = getRed() + getColorCount(getAllPowerUps(), RED);
        if(availableRed < invoice.getRedAmmos()) return false;

        int availableYellow = getYellow() + getColorCount(getAllPowerUps(), YELLOW);
        if(availableYellow < invoice.getYellowAmmos()) return false;

        int availableBlue = getBlue() + getColorCount(getAllPowerUps(), BLUE);
        if(availableBlue < invoice.getBlueAmmos()) return false;

        return true;


    }

    private int getColorCount(List<PowerUpCard> list, AmmoColor color) {
        int i = 0;
        for(PowerUpCard e : list) {
            if(e.getEquivalentAmmo() == color) {
                i++;
            }
        }
        return i;
    }
}
