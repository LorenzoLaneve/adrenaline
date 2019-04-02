package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import java.util.List;

public class EffectSet extends ConcreteEffect{

    private List<Effect> set;

    public EffectSet(int id, List<Effect> set) {
        super(id);
        //TODO
    }

    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }
}
