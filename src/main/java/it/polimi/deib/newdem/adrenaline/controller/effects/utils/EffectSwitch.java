package it.polimi.deib.newdem.adrenaline.controller.effects.utils;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.*;

public class EffectSwitch {

    private List<Integer> choices;

    private Map<Integer, Effect> branches;

    private EffectSwitch(List<Integer> choices) {
        this.choices = new ArrayList<>(choices);
        this.branches = new HashMap<>();
    }


    public static EffectSwitch create(Integer... choices) {
        return create(Arrays.asList(choices));
    }

    public static EffectSwitch create(List<Integer> choices) {
        return new EffectSwitch(choices);
    }

    public EffectSwitch when(Integer choice, Effect toRun) {
        branches.put(choice, toRun);
        return this;
    }

    public void execute(EffectManager manager, Player actor) throws UndoException {

        do {
            Integer choice = manager.choose(choices);
            if (choice == null) {
                break;
            }

            Effect toRun = branches.get(choice);
            if (choices.contains(choice) && toRun != null) {
                toRun.apply(manager, actor);
            } else {
                break;
            }

            choices.remove(choice);
        } while (!choices.isEmpty());

    }

    public void executeOne(EffectManager manager, Player actor) throws UndoException {
        Integer choice = manager.choose(choices);

        Effect toRun = branches.get(choice);
        if (choices.contains(choice) && toRun != null) {
            toRun.apply(manager, actor);
        }
    }

}
