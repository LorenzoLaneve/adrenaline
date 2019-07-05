package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.DamageBoard;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.action_board.ActionBoard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

/**
 * Implementers behave like a playing character in an Adrenaline game.
 */
public interface Player {

    /**
     * Initializes this player.
     *
     * This player's new inventory, damage and action boards are created.
     */
    void init();

    /**
     * Returns the game this player is playing in.
     * @return the {@code Game} this player belongs to
     */
    Game getGame();

    /**
     * Retrieves the listener associated with this {@code Player}
     * @return this {@code Player}'s listener
     */
    PlayerListener getListener();

    /**
     * Returns the name of the user associated to this player.
     * @return name
     */
    String getName();

    /**
     * Returns the tile this player is located in, or {@code null} if the player is out of the map.
     * @return this player's {@code Tile}
     */
    Tile getTile();

    /**
     * Sets the this player's location to the given tile.
     * @param dest this player's new location
     */
    void setTile(Tile dest);

    /**
     * Returns a PlayerInventory object describing the player's inventory, containing all of their resources.
     * @return this player's {@code PlayerInventory} object. USe with care.
     */
    PlayerInventory getInventory();

    /**
     * Returns the player's color.
     * @return this {@code Player}'s color
     */
    PlayerColor getColor();

    /**
     * Retrieves a list of all the legal actions this player can take at the time of invocation.
     *
     * This depends on powerups in inventory, action boards' state, damage taken.
     *
     * @return List of actions this player may perform
     */
    List<ActionFactory> getMoves();

    /**
     * Retrieves the amount of actions that can be performed this turn
     *
     * @return amount of actions that can be performed this turn
     */
    int getMovesAmount();

    /**
     * Returns the number of times this player died.
     * @return number of deaths
     */
    int getDeaths();

    /**
     * Returns the number of damage this player currently has.
     * @return total damage taken
     */
    int getTotalDamage();

    /**
     * Identifies the player that dealt the {@code cell}-th damage to this player.
     *
     * @param cell the index of the cell. Between 0 and 11 inclusive.
     * @return player who dealt the damage. Can be null.
     */
    Player getDamager(int cell);

    /**
     * Returns the total damage that this player received from the given player since their (re)spawn.
     * @return amount of damage taken
     */
    int getDamageFromPlayer(Player player);

    /**
     * Returns the total number of marks that this player received from the given player.
     * @return amount of marks taken
     */
    int getMarksFromPlayer(Player player);

    /**
     * Returns whether the player is dead in the way that the game Adrenaline means death.
     * @return is this dead
     */
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

    /**
     * Gives the first player card to this player.
     */
    void assignFirstPlayerCard();

    /**
     * Returns whether this player has the first player card, and therefore whether
     * they are the first player that needs to make their move.
     * @return does this player have the first player card
     */
    boolean hasFirstPlayerCard();

    /**
     * Gives the given points to the player.
     * @param points to add to this player's score
     */
    void addScore(int points);

    /**
     * Returns the total score this player currently has.
     * @return current score
     */
    int getScore();

    /**
     * Assigns the given {@code PlayerListener} to this player
     * @param listener to assign to this player
     */
    void setListener(PlayerListener listener);

    /**
     * Returns whether this player has an open connection.
     * @return does this user have an open connection
     */
    boolean isConnected();

    /**
     * Makes the player draw a new power up card from the game's power up deck.
     */
    void drawCard();

    /**
     * Returns the damage board owned by this player.
     * @see DamageBoard for further information.
     */
    DamageBoard getDamageBoard();

    /**
     * Returns the action board owned by this player.
     * @see ActionBoard for further information
     */
    ActionBoard getActionBoard();

    /**
     * Used to report death and allow resurrection with Undo.
     * @param isDead death status of this player
     */
    void reportDeath(boolean isDead);

    /**
     * Add skull on killtrack.
     */
    void addSkull();

    /**
     * Checks whether this player's action board is in any Frenzy state.
     *
     * @return is this player's action board frenzy
     */
    boolean isActionBoardFrenzy();

    /**
     * Generates a new snapshot of this {@code Player}'s state
     *
     * @return current player state to be sent to the listener.
     */
    PlayerData generatePlayerData();

    /**
     * Verifies if this player is eligible for a reload action
     * @return is this player eligible for a reload action
     */
    boolean canReload();

    /**
     * Checks if the player died this turn and should assign score for their death.
     * @return did this player die this turn, or has this player's damage board been scored
     */
    boolean diedThisTurn();

    /**
     * Notifies the model object that the scores have been assigned for their death.
     */
    void resetTurnDeath();

}
