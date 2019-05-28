package it.polimi.deib.newdem.adrenaline.model.game.utils;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class ScoreboardEntry {
    private final Player p;
    private final int totalScore;
    private final int earliestShot;

    public ScoreboardEntry(Player p, int totalScore, int earliestShot) {
        this.p = p;
        this.totalScore = totalScore;
        this.earliestShot = earliestShot;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getEarliestShot() {
        return earliestShot;
    }

    public Player getPlayer() {
        return p;
    }
}
