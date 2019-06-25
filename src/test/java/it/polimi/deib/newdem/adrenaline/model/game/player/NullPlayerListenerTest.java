package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.model.game.MockPlayer;
import it.polimi.deib.newdem.adrenaline.model.game.MockPowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.game.MockWeaponCard;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NullPlayerListenerTest {

    NullPlayerListener npl;

    @Before
    public void setUp() throws Exception {
        npl = new NullPlayerListener();
    }

    @Test
    public void testPlayerDidDrawPowerUpCard() throws Exception {
        npl.playerDidDrawPowerUpCard(new MockPowerUpCard());
    }

    @Test
    public void testPlayerDidDiscardPowerUpCard() throws Exception {
        npl.playerDidDiscardPowerUpCard(new MockPowerUpCard());
    }

    @Test
    public void testPlayerDidGrabDrop() throws Exception {
        npl.playerDidGrabDrop(
                new DropInstance(
                        new AmmoSet(1,1,1),
                        false
                )
        );
    }

    @Test
    public void testPlayerDidGrabWeapon() throws Exception {
        npl.playerDidGrabWeapon(new MockWeaponCard("Gun"));
    }

    @Test
    public void testPlayerDidDiscardWeapon() throws Exception {
        npl.playerDidDiscardWeapon(new MockWeaponCard("Gun"));
    }
}