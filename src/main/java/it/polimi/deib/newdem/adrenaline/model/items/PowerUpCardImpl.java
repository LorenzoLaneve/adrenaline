package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;

public class PowerUpCardImpl implements PowerUpCard {

    private Effect effect;

    private int cardID;

    private PowerUpTrigger trigger;

    private AmmoColor equivalentAmmo;


    public PowerUpCardImpl(Effect effect, int cardID, PowerUpTrigger trigger, AmmoColor equivAmmo) {
        this.effect = effect;
        this.cardID = cardID;
        this.trigger = trigger;
        this.equivalentAmmo = equivAmmo;
    }


    @Override
    public Effect getEffect() {
        return effect;
    }

    @Override
    public int getCardID() {
        return cardID;
    }

    @Override
    public PowerUpTrigger getTrigger() {
        return trigger;
    }

    @Override
    public AmmoColor getEquivalentAmmo() {
        return equivalentAmmo;
    }


}
