package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.ActionFactory;

import java.util.List;

public interface Player {

    Game getGame();

    String getName();

    PlayerInventory getInventory();

    PlayerColor getColor();

    List<ActionFactory> getMoves();

    int getDeaths();

    int getTotalDamage();

    Player getDamager(int cell);

    int getDamageFromPlayer(Player player);

    int getMarksFromPlayer(Player player);

    boolean isDead();
}
