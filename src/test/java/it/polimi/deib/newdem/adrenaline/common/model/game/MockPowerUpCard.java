package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.common.model.items.PowerUpCard;

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
