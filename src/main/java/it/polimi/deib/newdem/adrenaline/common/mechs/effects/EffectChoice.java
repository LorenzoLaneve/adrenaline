package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import java.util.List;
import it.polimi.deib.newdem.adrenaline.common.mechs.actions.Action;

public class EffectChoice extends ConcreteEffect {

    private List<Effect> choices;

    public EffectChoice(int id, List<Effect> choices) {
        super(id);
        //TODO
    }

    @Override
    public void apply(Action action) {
        //TODO
    }
}
