package it.polimi.deib.newdem.adrenaline.controller.effects;

import java.util.List;

public class EffectSequence extends ConcreteEffect {

    private List<Effect> effectList;

    public EffectSequence(int id, List<Effect> effectList) {
        super(id);
        //TODO
    }

    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }
}
