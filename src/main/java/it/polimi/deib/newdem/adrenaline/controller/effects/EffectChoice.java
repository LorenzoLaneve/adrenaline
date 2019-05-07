package it.polimi.deib.newdem.adrenaline.controller.effects;

import java.util.List;

public class EffectChoice extends ConcreteEffect {

    private List<Effect> choices;

    public EffectChoice(int id, List<Effect> choices) {
        super(id);
        //TODO
    }

    @Override
    public void apply(EffectVisitor visitor) {
        //TODO
    }
}
