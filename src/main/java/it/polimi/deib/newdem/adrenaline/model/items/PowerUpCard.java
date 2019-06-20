package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;

public interface PowerUpCard {

    /**
     * Card ID that is used to identify hidden cards.
     */
    int HIDDEN = -1;


    Effect getEffect();

    int getCardID();

    PowerUpTrigger getTrigger();

    AmmoColor getEquivalentAmmo();

}
