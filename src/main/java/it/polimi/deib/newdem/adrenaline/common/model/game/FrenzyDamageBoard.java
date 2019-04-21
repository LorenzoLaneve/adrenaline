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

    @Override
    public List<ActionFactory> getAdditionalActions(int totalDamage) {
        // TODO implement
        return null;
    }

    @Override
    protected boolean shouldAssignFirstBlood() {
        return false;
    }
}
