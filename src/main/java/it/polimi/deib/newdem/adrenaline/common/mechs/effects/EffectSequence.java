package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import java.util.List;
import it.polimi.deib.newdem.adrenaline.common.mechs.actions.Action;

public class EffectSequence extends ConcreteEffect {

    private List<Effect> effectList;

    public EffectSequence(int id, List<Effect> effectList) {
        super(id);
        //TODO
    }

    @Override
    public void apply(Action action) {
        //TODO
    }
}
