package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.items.*;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals(0, inv.getPowerUps().size());
        PowerUpCard c1 = new MockPowerUpCard();
        inv.addPowerUp(c1);
        assertTrue(inv.getPowerUps().contains(c1));
        assertEquals(1,inv.getPowerUps().size());
    }

    @Test
    public void testGetRed() {
        assertEquals(0,inv.getRed());
    }

    @Test
    public void testGetBlue() {
        assertEquals(0,inv.getBlue());
    }

    @Test
    public void testGetYellow() {
        assertEquals(0,inv.getYellow());
    }

    @Test
    public void testAddAmmo() {
        assertEquals(0, inv.getRed());
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
        assertEquals(0,inv.getPowerUps().size());
        PowerUpCard c1 = new MockPowerUpCard();
        inv.addPowerUp(c1);
        assertTrue(inv.getPowerUps().contains(c1));
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
}