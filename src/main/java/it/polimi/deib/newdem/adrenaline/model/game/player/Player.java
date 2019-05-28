package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.DamageBoard;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
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

    int getScoreForPlayer(Player player);

    void assignFirstPlayerCard();

    void init();

    boolean hasFirstPlayerCard();
}
