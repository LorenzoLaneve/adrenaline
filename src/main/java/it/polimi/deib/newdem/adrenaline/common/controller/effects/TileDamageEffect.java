package it.polimi.deib.newdem.adrenaline.common.controller.effects;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.selection.TileSelectorFactory;

public class TileDamageEffect extends DamageEffect {

    private TileSelectorFactory selectorMaker;

    public TileDamageEffect(int id, int dmgAmt, int mrkAmt, TileSelectorFactory selectorMaker) {
        super(id, dmgAmt, mrkAmt);
        this.selectorMaker = selectorMaker;
        //TODO
    }

    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }
}
