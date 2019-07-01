package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.DamageBoard;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.action_board.ActionBoard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public interface Player {

    Game getGame();

    PlayerListener getListener();

    String getName();

    Tile getTile();

    void setTile(Tile dest);

    PlayerInventory getInventory();

    PlayerColor getColor();

    /**
     * @return List of action factory (action) from the player action board
     */
    List<ActionFactory> getMoves();

    /**
     * @return amount of actions that can be performed in a turn
     */
    int getMovesAmount();

    int getDeaths();

    int getTotalDamage();

    /**
     * Identifies the player that dealt the {@code cell}-th damage to this player.
     *
     * @param cell the index of the cell. Between 0 and 11 inclusive.
     * @return player who dealt the damage. Can be null.
     */
    Player getDamager(int cell);

    int getDamageFromPlayer(Player player);

    int getMarksFromPlayer(Player player);

    boolean isDead();

    /**
     * Bind a {@code DamageBoard} to this player
     * Note that if a previous damage board was set, its listener will be passed to the new damage board.
     *
     * @param damageBoard the board to register.
     */
    void registerDamageBoard(DamageBoard damageBoard);

    /**
     * Method used when going frenzy.
     * @param precedesFirstPlayer whether the current player is before or after the first player in the frenzy turn.
     */
    void goFrenzy(boolean precedesFirstPlayer);

    /**
     * Used to calculate the score after death of current player.
     * @param player to calculate the score of.
     * @return the score to add to player based on the damage done to this.
     */
    int getScoreForPlayer(Player player);

    void assignFirstPlayerCard();

    /**
     * Initializes this player.
     *
     * This player's new inventory, damage and action boards are created.
     */
    void init();

    boolean hasFirstPlayerCard();

    void addScore(int points);

    int getScore();

    void setListener(PlayerListener listener);

    boolean isConnected();

    void drawCard();

    DamageBoard getDamageBoard();

    ActionBoard getActionBoard();

    /**
     * Used to report death and allow resurrection with Undo.
     * @param isDead
     */
    void reportDeath(boolean isDead);

    /**
     * Add skull on killtrack.
     */
    void addSkull();

    boolean isActionBoardFrenzy();

    /**
     * @return initial player state to be sent to the listener.
     */
    PlayerData generatePlayerData();
}
