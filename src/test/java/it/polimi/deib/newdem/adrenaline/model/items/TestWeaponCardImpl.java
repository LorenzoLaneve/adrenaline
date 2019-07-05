package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.MachineGunEffect;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestWeaponCardImpl {

    WeaponCard drawnCard;

    /**
     * Testing correct loading of deck form json and basic functionality of the WeaponCard
     */
    @Before
    public void setUp() throws Exception {
        try {
            WeaponDeck.loadCardsFromJson("cards/weapons.json");
        } catch (DeckAlreadyLoadedException x) {
            // ok
        }

        Deck<WeaponCard> actualDeck = WeaponDeck.createNewDeck();

        drawnCard = actualDeck.draw();

        while (drawnCard.getCardID() != 3){
            drawnCard = actualDeck.draw();
        }
    }

    @Test
    public void getPickupPrice() {
        PaymentInvoice pInvc = new PaymentInvoice(1,0,0,0);

        assertEquals(pInvc.getRedAmmos(), drawnCard.getPickupPrice().getRedAmmos());
        assertEquals(pInvc.getBlueAmmos(), drawnCard.getPickupPrice().getBlueAmmos());
        assertEquals(pInvc.getYellowAmmos(), drawnCard.getPickupPrice().getYellowAmmos());
        assertEquals(pInvc.getAnyAmmos(), drawnCard.getPickupPrice().getAnyAmmos());
    }

    @Test
    public void getReloadPrice() {
        PaymentInvoice pInvc = new PaymentInvoice(1,1,0,0);

        assertEquals(pInvc.getRedAmmos(), drawnCard.getReloadPrice().getRedAmmos());
        assertEquals(pInvc.getBlueAmmos(), drawnCard.getReloadPrice().getBlueAmmos());
        assertEquals(pInvc.getYellowAmmos(), drawnCard.getReloadPrice().getYellowAmmos());
        assertEquals(pInvc.getAnyAmmos(), drawnCard.getReloadPrice().getAnyAmmos());
    }

    @Test
    public void getEffect() {
        assertTrue(drawnCard.getEffect() instanceof MachineGunEffect);
    }

    @Test
    public void makeWeapon() {
        Player player = null;
        assertEquals(3, drawnCard.makeWeapon(player).getCard().getCardID());
    }

    @Test
    public void getCardID() {
        assertEquals(3,drawnCard.getCardID());
    }
}