package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.ActionFactory;

import java.util.List;

public class OrdinaryDamageBoard extends DamageBoardImpl {

    public OrdinaryDamageBoard(Player player) {
        super(player);
        // TODO implement
    }

    @Override
    public List<ActionFactory> getAdditionalMoves(int totalDamage) {
        return null;
    }

    @Override
    public int getScoreForPlayer(Player player) {
        return 0;
    }

}
