package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.model.items.*;
import it.polimi.deib.newdem.adrenaline.model.map.WeaponAlreadyPresentException;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static it.polimi.deib.newdem.adrenaline.model.items.AmmoColor.BLUE;
import static it.polimi.deib.newdem.adrenaline.model.items.AmmoColor.RED;
import static it.polimi.deib.newdem.adrenaline.model.items.AmmoColor.YELLOW;
import static java.lang.Integer.min;

public class PlayerInventory {

    private Player player;
    private List<PowerUpCard> powerUpCards;
    private List<Weapon> weapons;
    private static final int MAX_EQUIPMENT = 4;
    private static final int MAX_WEAPONS = 3;
    private static final int MAX_AMMO = 3;
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
        ammos.put(RED, 0);
        ammos.put(BLUE, 0);
        ammos.put(YELLOW, 0);
    }

    /**
     * Get a reference to the owner of this inventory
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
     * Get all the powerups vurrently in the inventory
     *
     * @return powerups
     */
    public List<PowerUpCard> getPowerUps(){
        List<PowerUpCard> cards = new ArrayList<>();
        for(PowerUpCard p : powerUpCards) {
            cards.add(p);
        }
        return new ArrayList<>(cards);
    }

    public int getRed() {
        return ammos.get(RED);
    }

    public int getBlue() {
        return ammos.get(BLUE);
    }

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
        ammos.put(color, min(ammos.get(color) + amount, MAX_AMMO));

        AmmoSet ammoSet = new AmmoSet(0,0,0);

        switch (color){
            case RED:
                ammoSet = new AmmoSet(amount, 0, 0);
                break;
            case BLUE:
                ammoSet = new AmmoSet(0, 0, amount);
                break;
            case YELLOW:
                ammoSet = new AmmoSet(0,amount, 0);
                break;
        }

        player.getListener().playerDidReceiveAmmos(player, ammoSet);
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
        for(Weapon w : weapons) {
            if(w.getCard().equals(weapon.getCard())){
                //TODO enrich .equals()
                weapons.remove(w);
                player.getListener().playerDidDiscardWeaponCard(player, weapon.getCard());
                break;
            }
        }
    }

    public boolean canAcceptPowerUp() {
        return powerUpCards.size() < MAX_EQUIPMENT - 1;
    }

    public void addPowerUp(PowerUpCard card) throws OutOfSlotsException {
        if(null == card) throw new IllegalArgumentException();
        if(!canAcceptPowerUp()) throw new OutOfSlotsException();
        powerUpCards.add(card);
        player.getListener().playerDidDiscardPowerUpCard(player, card);
    }

    public void removeAmmo(AmmoColor color, int amount){
        if(amount < 0) throw new IllegalArgumentException();
        ammos.put(color, min(ammos.get(color) - amount, MAX_AMMO));

        AmmoSet ammoSet = new AmmoSet(0,0,0);

        switch (color){
            case RED:
                ammoSet = new AmmoSet(amount, 0, 0);
                break;
            case BLUE:
                ammoSet = new AmmoSet(0, 0, amount);
                break;
            case YELLOW:
                ammoSet = new AmmoSet(0,amount, 0);
                break;
        }

        player.getListener().playerDidDiscardAmmos(player, ammoSet);

    }

    public void removePowerUp(List<PowerUpCard> powerUpCardList){
        powerUpCards.removeAll(powerUpCardList);
        for(PowerUpCard card: powerUpCardList){
            player.getListener().playerDidReceivePowerUpCard(player, card);
        }

    }
}
