package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpTrigger;

public class MockPowerUpCard implements PowerUpCard {

    /**
     * Simple Mock Object Used for testing purposes.
     */

    @Override
    public Effect getEffect() {
        return null;
    }

    @Override
    public int getCardID() {
        return 0;
    }

    @Override
    public PowerUpTrigger getTrigger() {
        return PowerUpTrigger.CALL;
    }

    @Override
    public AmmoColor getEquivalentAmmo() {
        return null;
    }

}
