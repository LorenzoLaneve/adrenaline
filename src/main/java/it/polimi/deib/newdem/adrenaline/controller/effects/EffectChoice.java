package it.polimi.deib.newdem.adrenaline.controller.effects;

import java.util.ArrayList;
import java.util.List;

public class EffectChoice extends ConcreteEffect {

    private final List<Effect> choices;

    private final int iterations;

    public EffectChoice(int id, List<Effect> choices, int iterations) {
        super(id);

        this.choices = new ArrayList<>(choices);
        this.iterations = iterations;
    }

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {

        Effect chosenEffect = visitor.chooseEffect(new ArrayList<>(choices));

        List<Effect> leftChoices = new ArrayList<>(choices);
        leftChoices.remove(chosenEffect);

        visitor.scheduleEffect(chosenEffect);

        if (iterations > 1) {
            visitor.scheduleEffect(new EffectChoice(Effect.DYNAMIC, leftChoices, iterations - 1));
        }

    }
}
