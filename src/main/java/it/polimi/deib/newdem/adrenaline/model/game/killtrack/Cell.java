package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Object used in the implementation {@code KillTrackImpl} to store information about a kill
 * made by players during the game.
 */
class Cell {

    private final Player killer;
    private final int amount;

    /**
     * Creates a new {@code Cell} with the given {@code Player} and {@code Color}
     * @param killer player who dealt the killing shot
     * @param amount amount of counters on this cell
     */
    public Cell(Player killer, int amount) {
        this.killer = killer;
        this.amount = amount;
    }

    /**
     * Returns the player that made the kill.
     */
    public Player getKiller() {
        return killer;
    }

    /**
     * Returns the amount of points given for that kill.
     * 1 for normal kill, 2 for overkill, as stated by Adrenaline's rules.
     */
    public int getAmount() {
        return amount;
    }
}