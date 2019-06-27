package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.ConcreteActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Damage board used when the game is not in frenzy mode.
 */
public class OrdinaryDamageBoard extends DamageBoardImpl {

    public static final int GRAB2_TRESHOLD = 2;
    public static final int MOVESHOOT_TRESHOLD = 5;

    public OrdinaryDamageBoard(Player player) {
        super(player);
        score = new ArrayList<>(Arrays.asList(8,6,4,2,1,1));
    }

    /**
     * Returns the {@code ActionFactory} enables by the current damage.
     * In this concrete class, this will always be nothing.
     *
     * @return the additional actions
     */
    @Override
    public List<ActionFactory> getAdditionalActions() {
        ArrayList<ActionFactory> out = new ArrayList<>();
        if(getTotalDamage() > GRAB2_TRESHOLD) {
            out.add(new ConcreteActionFactory(
                    AtomicActionType.MOVE2,
                    AtomicActionType.GRAB
            ));
        }

        if(getTotalDamage() > MOVESHOOT_TRESHOLD) {
            out.add(new ConcreteActionFactory(
                    AtomicActionType.MOVE1,
                    AtomicActionType.SHOOT
                    ));
        }

        return out;
    }

    @Override
    public boolean shouldAssignFirstBlood() {
        return true;
    }
}
