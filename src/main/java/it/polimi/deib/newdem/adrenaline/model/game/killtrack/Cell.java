package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

// package-private
class Cell {
    private final Player killer;
    private final int amount;

    public Cell(Player killer, int amount) {
        this.killer = killer;
        this.amount = amount;
    }

    public Player getKiller() {
        return killer;
    }

    public int getAmount() {
        return amount;
    }
}
