package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionFactory;

import java.util.List;

public interface DamageBoard {

    Player getPlayer();

    List<ActionFactory> getAdditionalActions(int totalDamage);

    int getScoreForPlayer(Player player);

}
