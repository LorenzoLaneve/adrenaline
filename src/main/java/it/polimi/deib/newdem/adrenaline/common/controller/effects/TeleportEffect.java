package it.polimi.deib.newdem.adrenaline.common.controller.effects;

public class TeleportEffect extends ConcreteEffect {


    public TeleportEffect(int id) {
        super(id);
        //TODO
    }


    /** Implements the teleport effect.
     *
     * Using {@code visitor}, this mehod requests target teleport tile
     * and register the associate {@code GameChange} in {@code visitor}
     *
     * ***
     *
     * Interacting with @code{visitor},
     * the relevant changes to gameplay are calculated and registered
     * in an appropriate GameChange in @code{visitor}.
     *
     * @param visitor Interface with gameplay
     */
    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }
}
