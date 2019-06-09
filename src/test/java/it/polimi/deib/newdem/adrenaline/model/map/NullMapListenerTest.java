package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.MockPlayer;
import it.polimi.deib.newdem.adrenaline.model.game.MockWeaponCard;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NullMapListenerTest {

    private NullMapListener l;
    private Tile t1;
    private Tile t2;
    private DropInstance dropInstance;

    @Before
    public void setUp() throws Exception {
        l = new NullMapListener();
        t1 = new OrdinaryTile(new TilePosition(0,0));
        t2 = new SpawnPointTile(new TilePosition(0,1), AmmoColor.RED);
        dropInstance = new DropInstance(
                new AmmoSet(
                        1,1,0
                ),
                true
        );
    }

    @Test
    public void testPlayerDidMove() throws Exception {
        l.playerDidMove(new MockPlayer(), t1, t2);
    }

    @Test
    public void testPlayerDidSpawn() throws Exception {
        l.playerDidSpawn(new MockPlayer(), t2);
    }

    @Test
    public void testDropDidSpawn() throws Exception {
        l.dropDidSpawn(t1, dropInstance);
    }

    @Test
    public void testPlayerDidDie() throws Exception {
        l.playerDidDie(new MockPlayer());
    }

    @Test
    public void testPlayerDidResurrect() throws Exception {
        l.playerDidResurrect(new MockPlayer());
    }

    @Test
    public void testPlayerDidLeaveMap() throws Exception {
        l.playerDidLeaveMap(new MockPlayer());
    }

    @Test
    public void testMapDidSendTileData() throws Exception {
        l.mapDidSendTileData(new ArrayList<>());
    }

    @Test
    public void testMapDidSendSpawnPointData() throws Exception {
        l.mapDidSendSpawnPointData(new ArrayList<>());
    }

    @Test
    public void testWeaponDidSpawn() throws Exception {
        l.weaponDidSpawn(t1, new MockWeaponCard("gun"));
    }

    @Test
    public void testPlayerDidGrabDrop() throws Exception {
        l.playerDidGrabDrop(new MockPlayer(), dropInstance, t1);
    }
}