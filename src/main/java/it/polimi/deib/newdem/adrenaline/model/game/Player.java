package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public interface Player {

    Game getGame();

    String getName();

    Tile getTile();

    void setTile(Tile dest);

    PlayerInventory getInventory();

    PlayerColor getColor();

    List<ActionFactory> getMoves();

    int getDeaths();

    int getTotalDamage();

    Player getDamager(int cell);

    int getDamageFromPlayer(Player player);

    int getMarksFromPlayer(Player player);

    boolean isDead();

    void registerDamageBoard(DamageBoard damageBoard);

    void takeDamage(int dmgAmount, Player attacker);

    void takeMark(int markAmount, Player attacker);

    void goFrenzy(boolean precedesFirstPlayer);

    void assignFirstPlayerCard();

    void init();

    boolean hasFirstPlayerCard();
}
