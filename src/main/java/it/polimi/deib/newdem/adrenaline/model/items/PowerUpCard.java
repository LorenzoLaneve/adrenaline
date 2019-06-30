package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;

public interface PowerUpCard extends Card{

    /**
     * Card ID that is used to identify hidden cards.
     */
    int HIDDEN = -1;

    Effect getEffect();


    /**
     * @return the event that triggers a possible call to the powerup.
     */
    PowerUpTrigger getTrigger();

    /**
     * @return the color of the card.
     */
    AmmoColor getEquivalentAmmo();

}
