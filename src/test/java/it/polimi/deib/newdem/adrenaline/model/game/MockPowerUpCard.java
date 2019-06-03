package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
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

    @Override
    public int getCardID() {
        return 0;
    }

    @Override
    public AmmoColor getColor() {
        return null;
    }
}
