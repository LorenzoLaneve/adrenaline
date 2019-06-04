package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.*;


public class FrenzyDamageBoard extends DamageBoardImpl {

    /**
     * Builds a {@code FrenzyDamageBoard} for this {@code Player} with the given {@code marks}
     *
     * @param player the player this board belongs to
     */
    public FrenzyDamageBoard(Player player, Map<Player, Integer> marks) {
        super(player);
        this.marks = new HashMap<>(marks);
        setDeathScoreVector();
    }

    /**
     * Builds a {@code FrenzyDamageBoard} for this {@code Player}
     *
     * @param player this board belongs to
     */
    public FrenzyDamageBoard(Player player) {
        super(player);
        setDeathScoreVector();
    }

    private void setDeathScoreVector() {
        score = new ArrayList<>(Arrays.asList(2,1,1,1));
    }

    /**
     * Returns the {@code ActionFactory} enables by the current damage.
     * In this concrete class, this will always be nothing.
     *
     * @return additional actions
     */
    @Override
    public List<ActionFactory> getAdditionalActions() {
        return new ArrayList<>();
    }

    @Override
    public boolean shouldAssignFirstBlood() {
        return false;
    }
}
