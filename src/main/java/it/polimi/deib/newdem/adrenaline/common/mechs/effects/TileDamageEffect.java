package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

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
