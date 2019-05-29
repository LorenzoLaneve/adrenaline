package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelectorFactory;

public class KnockbackEffect extends ConcreteEffect {

    private int minDistance;
    private int maxDistance;

    public KnockbackEffect(int id, MetaPlayer target, int knockBackDist, TileSelectorFactory selectorFactory) {
        super(id);
        //TODO
    }

    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }
}
