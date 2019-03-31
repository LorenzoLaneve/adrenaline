package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.ActionFactory;

import java.util.List;

public class FrenzyDamageBoard extends DamageBoardImpl {

    public FrenzyDamageBoard(Player player) {
        super(player);
        // TODO implement
    }

    @Override
    public List<ActionFactory> getAdditionalMoves(int totalDamage) {
        // TODO implement
        return null;
    }

    @Override
    public int getScoreForPlayer(Player player) {
        // TODO implement
        return 0;
    }
}
