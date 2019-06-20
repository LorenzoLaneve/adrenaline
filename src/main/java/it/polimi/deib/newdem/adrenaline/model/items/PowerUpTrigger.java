package it.polimi.deib.newdem.adrenaline.model.items;

public enum PowerUpTrigger {

    /**
     * The card can be applied when the owner selects it during their own turn.
     */
    CALL,

    /**
     * The card can be applied when the owner is damaged by another player.
     */
    DAMAGE_TAKEN,

    /**
     * The card can be applied when the owner deals damage to another player.
     */
    DAMAGE_DEALT

}
