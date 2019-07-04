package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Object used in the implementation of KillTrackImpl to store information about a kill
 * made by players during the game.
 */
class Cell {

    private final Player killer;
    private final int amount;

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