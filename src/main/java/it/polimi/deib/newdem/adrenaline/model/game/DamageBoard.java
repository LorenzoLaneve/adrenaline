package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;
import java.util.Map;

/**
 * Represents a player's damage board, keeping track of the various damage taken from other players.
 */
public interface DamageBoard {

    /**
     * Returns the player that owns this damage board.
     */
    Player getPlayer();

    /**
     * Returns the additional actiona unlocked based on the current damage taken.
     */
    List<ActionFactory> getAdditionalActions();

    /**
     * Returns the score given to a player due to the death of this damage board owner.
     */
    int getScoreForPlayer(Player player);

    /**
     * Returns the player that made the damage in the given slot.
     */
    Player getDamager(int index);

    /**
     * Returns the total damage dealt by the given player to this damage board's owner.
     */
    int getTotalDamageFromPlayer(Player player);

    /**
     * Returns the number of marks dealt by the given player to this damage board's owner.
     */
    int getTotalMarksFromPlayer(Player player);

    /**
     * Returns a Map object containing the number of marks dealt by each player.
     */
    Map<Player, Integer> getMarksMap();

    /**
     * Returns the total damage dealt to this damage board's owner.
     */
    int getTotalDamage();

    /**
     * Sets the number of marks held to this damage board from the given player to the given value.
     */
    void setMarksFromPlayer(int totalMarks, Player player);

    /**
     * Returns whether the damage board should assign score for the first blood.
     */
    boolean shouldAssignFirstBlood();

    /**
     * Adds a damage from the given player to the damage board.
     * @throws DamageTrackFullException if the damage board is full and no other damage is allowed to be added.
     */
    void appendDamage(Player player) throws DamageTrackFullException;

    /**
     * Removes the last damage from the damage board.
     * @throws DamageTrackEmptyException if no damage is present in the damage board.
     */
    Player popDamage() throws DamageTrackEmptyException;
}
