package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.Card;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;

import java.util.List;
import java.util.stream.Collectors;

public class PowerUpAtom extends AtomContext {

    public PowerUpAtom(AtomsContainer parent) {
        super(parent);
    }

    @Override
    public void execute() throws ConnectionException {
        // choose pup card
        List<PowerUpCard> availablePups = getActor().getInventory().getPowerUps();
        List<Integer> ids = availablePups.stream().map(Card::getCardID).collect(Collectors.toList());
        PowerUpCard selectedPup = null;

        do {
            try {
                int selectedId = parent.getDataSource().actionDidRequestChoice(ids);
                for (PowerUpCard pup : availablePups) {
                    if (pup.getCardID() == selectedId) {
                        selectedPup = pup;
                    }
                    if (null == selectedPup) throw new IllegalStateException();

                    // run it
                    selectedPup.getEffect().apply(new EffectManager(this), getActor());
                }
            } catch (UndoException e) {
                // force choice
            }
        }
        while (null != selectedPup);
    }
}
