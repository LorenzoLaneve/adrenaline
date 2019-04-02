package it.polimi.deib.newdem.adrenaline.common.controller.effects;

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
