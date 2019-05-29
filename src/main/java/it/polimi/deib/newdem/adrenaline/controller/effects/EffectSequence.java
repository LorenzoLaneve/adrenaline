package it.polimi.deib.newdem.adrenaline.controller.effects;

import java.util.ArrayList;
import java.util.List;

public class EffectSequence extends ConcreteEffect {

    private List<Effect> effectList;

    public EffectSequence(int id, List<Effect> effectList) {
        super(id);

        this.effectList = new ArrayList<>(effectList);
    }

    @Override
    public void apply(EffectVisitor visitor) {

        for (Effect effect : effectList) {
            visitor.scheduleEffect(effect);
        }

    }
}
