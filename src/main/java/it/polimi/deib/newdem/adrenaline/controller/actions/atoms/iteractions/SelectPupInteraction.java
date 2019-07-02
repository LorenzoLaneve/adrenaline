package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpTrigger;

import java.util.List;
import java.util.stream.Collectors;

public class SelectPupInteraction extends InteractionBase {

    public SelectPupInteraction(InteractionContext context) {
        super(context);
    }

    @Override
    public void execute() throws UndoException {
        List<PowerUpCard> availablePups = context.getActor().getInventory().getCallablePowerUps();

        PowerUpCard selectedPup = context.getDataSource().choosePowerUpCard(availablePups);

        if(null != selectedPup) {
            context.pushInteraction(new ActivatePupInteraction(context, selectedPup));
        }
        /*
        if (selectedPup != null) {
            try {
                EffectManager manager = new EffectManager(this);
                manager.execute(selectedPup.getEffect());
            } catch (UndoException x) {
                // nothing to do here
            }
        }
        */
        /*
        * List<PowerUpCard> availablePups = getActor().getInventory().getPowerUps()
                .stream()
                .filter(card -> card.getTrigger() == PowerUpTrigger.CALL)
                .collect(Collectors.toList());

        PowerUpCard selectedPup = parent.getDataSource().choosePowerUpCard(availablePups);
        if (selectedPup != null) {
            try {
                EffectManager manager = new EffectManager(this);
                manager.execute(selectedPup.getEffect());
            } catch (UndoException x) {
                // nothing to do here
            }
        }
        * */
    }

    @Override
    public void revert() {
        // no changes were made
    }

    @Override
    public boolean requiresInput() {
        return true;
    }
}
