package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

public class MockPowerUpCard implements PowerUpCard {
    @Override
    public PaymentInvoice getUsePrice() {
        return null;
    }

    @Override
    public Effect getEffect() {
        return null;
    }
}
