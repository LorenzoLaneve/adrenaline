package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;

public interface PowerUpCard extends Card{

    /**
     * Card ID that is used to identify hidden cards.
     */
    int HIDDEN = -1;


    Effect getEffect();



    PowerUpTrigger getTrigger();

    AmmoColor getEquivalentAmmo();

}
