package it.polimi.deib.newdem.adrenaline.controller.effects.utils;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.*;

/**
 * Helper methods used by effect that simple implements a switch that asks the user for a choice
 * and executes the associated effects.
 */
public class EffectSwitch {

    private List<Integer> choices;

    private Map<Integer, Effect> branches;

    private EffectSwitch(List<Integer> choices) {
        this.choices = new ArrayList<>(choices);
        this.branches = new HashMap<>();
    }


    /**
     * Creates a new effect switch with the given legal choices.
     */
    public static EffectSwitch create(Integer... choices) {
        return create(Arrays.asList(choices));
    }

    /**
     * Creates a new effect switch with a list of the legal choices.
     */
    public static EffectSwitch create(List<Integer> choices) {
        return new EffectSwitch(choices);
    }

    /**
     * Adds the given effect to be called when the user chooses the given choice as integer.
     */
    public EffectSwitch when(Integer choice, Effect toRun) {
        branches.put(choice, toRun);
        return this;
    }

    /**
     * Starts the effect switch by asking the user a choice and executing the choices until
     * user wants to stop or until there are no legal choices left.
     * @param manager The EffectManager class used to ask the user the choices
     * @param actor The actor that will executes the chosen effects (i.e. the one who is choosing).
     * @throws UndoException if the user requested an undo during the choice
     */
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

    /**
     * Starts the effect switch by asking the user a choice and executing the choice ONCE.
     * @param manager The EffectManager class used to ask the user the choices
     * @param actor The actor that will executes the chosen effects (i.e. the one who is choosing).
     * @throws UndoException if the user requested an undo during the choice
     */
    public void executeOne(EffectManager manager, Player actor) throws UndoException {
        Integer choice = manager.choose(choices);

        Effect toRun = branches.get(choice);
        if (choices.contains(choice) && toRun != null) {
            toRun.apply(manager, actor);
        }
    }

}
