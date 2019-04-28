package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionFactory;

import java.util.*;


public class FrenzyDamageBoard extends DamageBoardImpl {

    /**
     * Builds a {@code FrenzyDamageBoard} for this {@code Player}
     *
     * @param player the player this board belongs to
     */
    public FrenzyDamageBoard(Player player) {
        super(player);
        score = new ArrayList<>(Arrays.asList(2,1,1,1));
    }

    /**
     * Returns the {@code ActionFactory} enables by the current damage.
     * In this concrete class, this will always be nothing.
     *
     * @return the additional actions
     */
    @Override
    public List<ActionFactory> getAdditionalActions() {
        return new ArrayList<>();
    }

    @Override
    protected boolean shouldAssignFirstBlood() {
        return false;
    }
}
