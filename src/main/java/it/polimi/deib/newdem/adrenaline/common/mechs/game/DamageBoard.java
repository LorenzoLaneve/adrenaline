package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.ActionFactory;

import java.util.List;

public interface DamageBoard {

    Player getPlayer();

    List<ActionFactory> getAdditionalMoves(int totalDamage);

    int getScoreForPlayer(Player player);

}
