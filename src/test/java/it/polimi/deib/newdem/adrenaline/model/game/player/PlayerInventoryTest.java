package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.model.game.MockPlayer;
import it.polimi.deib.newdem.adrenaline.model.game.MockPowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.game.MockWeapon;
import it.polimi.deib.newdem.adrenaline.model.game.MockWeaponCard;
import it.polimi.deib.newdem.adrenaline.model.items.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.deib.newdem.adrenaline.model.items.AmmoColor.RED;
import static org.junit.Assert.*;

public class PlayerInventoryTest {

    Player p;
    PlayerInventory inv;

    @Before
    public void setUp() {
        p = new MockPlayer();
        inv = new PlayerInventory(p);
    }

    @Test
    public void testGetPlayer() {
        assertEquals(inv.getPlayer(), p);
    }

    @Test
    public void testGetReadyWeapons() throws Exception{
        assertTrue(inv.getReadyWeapons().equalWeaponSet(new WeaponSet()));
        WeaponCard c1 = new MockWeaponCard("Gun");
        inv.addWeapon(new MockWeapon(c1,true));
        WeaponSet ws = new WeaponSet();
        ws.addWeapon(c1);
        assertTrue(inv.getReadyWeapons().equalWeaponSet(ws));

        inv.addWeapon(new MockWeapon("Rifle", false));
        assertTrue(inv.getReadyWeapons().equalWeaponSet(ws));
    }

    @Test
    public void testGetUnloadedWeapons() throws Exception {
        assertTrue(inv.getUnloadedWeapons().equalWeaponSet(new WeaponSet()));

        inv.addWeapon(new MockWeapon("Gun"));
        assertTrue(inv.getUnloadedWeapons().equalWeaponSet(new WeaponSet()));

        WeaponCard c1 = new MockWeaponCard("Rifle");
        WeaponSet ws = new WeaponSet();
        ws.addWeapon(c1);
        inv.addWeapon(new MockWeapon(c1, false));
        assertTrue(inv.getUnloadedWeapons().equalWeaponSet(ws));
    }

    @Test
    public void testGetPowerUps() throws Exception {
        assertEquals(0, inv.getAllPowerUps().size());
        PowerUpCard c1 = new MockPowerUpCard();
        inv.addPowerUp(c1);
        assertTrue(inv.getAllPowerUps().contains(c1));
        assertEquals(1,inv.getAllPowerUps().size());
    }

    @Test
    public void testGetRed() {
        assertEquals(3,inv.getRed());
    }

    @Test
    public void testGetBlue() {
        assertEquals(3,inv.getBlue());
    }

    @Test
    public void testGetYellow() {
        assertEquals(3,inv.getYellow());
    }

    @Test
    public void testAddAmmo() {
        assertEquals(3, inv.getRed());
        inv.removeAmmo(RED, 3);
        inv.addAmmo(RED, 2);
        assertEquals(2, inv.getRed());
    }

    @Test
    public void testAddWeapon() {
        Weapon w = new MockWeapon("Gun");
        inv.addWeapon(w);
        assertTrue(inv.getReadyWeapons().getWeapons().contains(w.getCard()));
    }

    @Test
    public void testRemoveWeapon() {
        Weapon w = new MockWeapon("Gun");
        inv.addWeapon(w);
        assertTrue(inv.getReadyWeapons().getWeapons().contains(w.getCard()));
        inv.removeWeapon(w);
        System.out.println(inv.getReadyWeapons().getWeapons().size());
        assertFalse(inv.getReadyWeapons().getWeapons().contains(w.getCard()));
    }

    @Test
    public void testAddPowerUp() throws Exception{
        assertEquals(0,inv.getAllPowerUps().size());
        PowerUpCard c1 = new MockPowerUpCard();
        inv.addPowerUp(c1);
        assertTrue(inv.getAllPowerUps().contains(c1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegative() {
        new PlayerInventory(null);
    }

    @Test
    public void testGetReadyWeaponsNegative() throws Exception {
            // the uncovered lines should be unreachable, I can't test them
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAmmoNegative() throws Exception {
        inv.addAmmo(RED, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWeaponNull() throws Exception {
        inv.addWeapon(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWeaponNull() throws Exception {
        inv.removeWeapon(null);
    }

    @Test
    public void testRemoveWeaponAbsent() throws Exception {
        inv.removeWeapon(new MockWeapon("Laser"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPowerUpNull() throws Exception {
        inv.addPowerUp(null);
    }

    @Test(expected = OutOfSlotsException.class)
    public void testAddPowerUpSmash() throws Exception {
        inv.addPowerUp(new MockPowerUpCard());
        inv.addPowerUp(new MockPowerUpCard());
        inv.addPowerUp(new MockPowerUpCard());
        inv.addPowerUp(new MockPowerUpCard());
    }

    @Test(expected = IllegalStateException.class)
    public void testAddWeaponSmash() throws Exception {
        inv.addWeapon(new MockWeapon("Gun"));
        inv.addWeapon(new MockWeapon("Rifle"));
        inv.addWeapon(new MockWeapon("Laser"));
        inv.addWeapon(new MockWeapon("Saw"));
    }

    //
    // New stuff
    //

    @Test
    public void testRemoveWeaponNegative() throws Exception {
        inv.addWeapon(new MockWeapon("Gun"));
        inv.removeWeapon(new MockWeapon("Rifle"));
    }

    @Test
    public void testRemoveAmmo() throws Exception {
        try {
            inv.removeAmmo(AmmoColor.RED, -1);
        }
        catch (IllegalArgumentException e) {
            // ok
        }
        catch (Exception e) {
            fail(); // not ok
        }
        inv.addAmmo(AmmoColor.RED, 2);
        inv.removeAmmo(AmmoColor.RED, 1);
    }

    @Test
    public void testRemovePowerUp() throws Exception {
        inv.removePowerUps(new ArrayList<>());
    }
}