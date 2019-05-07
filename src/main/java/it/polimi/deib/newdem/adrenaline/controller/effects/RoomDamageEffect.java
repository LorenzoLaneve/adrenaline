package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelectorFactory;

public class RoomDamageEffect extends DamageEffect {

    private TileSelectorFactory selectorMaker;

    public RoomDamageEffect(int id, int dmgAmt, int mrkAmt, TileSelectorFactory selectorMaker) {
        super(id, dmgAmt, mrkAmt);
        this.selectorMaker = selectorMaker;
        //TODO
    }
    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }

}
