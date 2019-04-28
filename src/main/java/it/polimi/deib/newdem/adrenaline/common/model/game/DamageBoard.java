package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionFactory;

import java.util.List;

public interface DamageBoard {

    Player getPlayer();

    List<ActionFactory> getAdditionalActions();

    int getScoreForPlayer(Player player);

    void takeDamage(int dmgAmount, Player attacker);

    void takeMark(int markAmount, Player attacker);

    Player getDamager(int index);

    int getTotalDamageFromPlayer(Player player);

    int getTotalMarksFromPlayer(Player player);

    int getTotalDamage();
}
