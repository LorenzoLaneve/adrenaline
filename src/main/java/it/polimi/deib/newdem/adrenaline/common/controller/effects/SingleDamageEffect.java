package it.polimi.deib.newdem.adrenaline.common.controller.effects;

public class SingleDamageEffect extends DamageEffect {

    private MetaPlayer metaPlayer;
    private PlayerSelectorFactory selectorMaker;


    public SingleDamageEffect(int id, int dmgAmt, int mrkAmt, MetaPlayer metaPlayer, PlayerSelectorFactory selectorMaker) {
        super(id, dmgAmt, mrkAmt);
        this.metaPlayer = metaPlayer;
        this.selectorMaker = selectorMaker;
        //TODO
    }

    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }
}
