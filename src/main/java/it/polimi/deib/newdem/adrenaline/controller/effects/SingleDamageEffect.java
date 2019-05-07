package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelectorFactory;

public class SingleDamageEffect extends DamageEffect {

    private MetaPlayer metaPlayer;
    private PlayerSelectorFactory selectorMaker;


    public SingleDamageEffect(int id, int dmgAmt, int mrkAmt, MetaPlayer metaPlayer, PlayerSelectorFactory selectorMaker) {
        super(id, dmgAmt, mrkAmt);
        this.metaPlayer = metaPlayer;
        this.selectorMaker = selectorMaker;
        //TODO
    }

    /** Implements the single (individual) damage efect.
     *
     * Calculates the variations associated with a damage effects
     * and generates the corresponding gameChange in @code{visitor}.
     *
     * @param visitor
     */
    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }
}
