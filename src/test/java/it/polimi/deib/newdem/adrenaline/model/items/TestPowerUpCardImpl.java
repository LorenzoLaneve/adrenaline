package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectLoader;
import it.polimi.deib.newdem.adrenaline.controller.effects.TagbackGrenadeEffect;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPowerUpCardImpl {

    PowerUpCard card;

    /**
     * Testing the correct functionality of PowerUpCard
     */
    @Before
    public void setUp() throws Exception {
        Effect cardEffect = EffectLoader.fromClass("it.polimi.deib.newdem.adrenaline.controller.effects.TagbackGrenadeEffect");
        card = new PowerUpCardImpl(cardEffect, 1, PowerUpTrigger.CALL, AmmoColor.BLUE);
    }

    @Test
    public void getEffect() {
        Effect effect = card.getEffect();

        assertTrue(effect instanceof TagbackGrenadeEffect);
    }

    @Test
    public void getCardID() {
        assertEquals(1, card.getCardID());
    }

    @Test
    public void getTrigger() {
        assertEquals(PowerUpTrigger.CALL, card.getTrigger());
    }

    @Test
    public void getColor() {
        assertEquals(AmmoColor.BLUE, card.getEquivalentAmmo());
    }

}