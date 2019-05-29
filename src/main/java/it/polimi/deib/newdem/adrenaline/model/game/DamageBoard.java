package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;
import java.util.Map;

public interface DamageBoard {

    Player getPlayer();

    List<ActionFactory> getAdditionalActions();

    int getScoreForPlayer(Player player);

    void takeDamage(int dmgAmount, Player attacker);

    void takeMark(int markAmount, Player attacker);

    Player getDamager(int index);

    int getTotalDamageFromPlayer(Player player);

    int getTotalMarksFromPlayer(Player player);

    Map<Player, Integer> getMarksMap();

    int getTotalDamage();
}
