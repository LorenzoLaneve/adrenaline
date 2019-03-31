package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.PowerUpSet;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.WeaponSet;

import java.util.List;

public interface Player {

    Game getGame();

    PlayerInventory getInventory();

    String getName();

    PlayerColor getColor();

    List<ActionFactory> getMoves();

    int getDeaths();

    int getTotalDamage();

    Player getDamager(int cell);

    int getDamageFromPlayer(Player player);

    int getMarksFromPlayer(Player player);

    boolean isDead();
}
