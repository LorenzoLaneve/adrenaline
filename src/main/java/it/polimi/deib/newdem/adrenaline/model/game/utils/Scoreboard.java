package it.polimi.deib.newdem.adrenaline.model.game.utils;

import it.polimi.deib.newdem.adrenaline.model.game.DamageBoardImpl;
import it.polimi.deib.newdem.adrenaline.model.game.Player;

import java.util.ArrayList;
import java.util.Comparator;

public class Scoreboard {
    private ArrayList<ScoreboardEntry> entries;

    private Comparator<ScoreboardEntry> damageboardComparator = (e1, e2) -> {
        if (null == e1 || null == e2) throw new IllegalArgumentException();
        else if (e1.getTotalScore() < e2.getTotalScore())
            return -1;
        else if (e1.getTotalScore() > e2.getTotalScore())
            return 1;
        else if (e1.getEarliestShot() > e2.getEarliestShot())
            return -1;
        else if (e1.getEarliestShot() < e2.getEarliestShot())
            return 1;
        else return 0;

    };

    public Scoreboard() {
        entries = new ArrayList<>();
    }

    public void registerEntry(ScoreboardEntry e) {
        entries.add(e);
    }

    /**
     * finds the given {@code Player}'s placement,
     * from 0 to the total amount of players minus one inclusive.
     *
     * If the player is not found, -1 is returned.
     *
     * @return this player's placement
     */
    public int getPlacement(Player p) {
        entries.sort(damageboardComparator.reversed());
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getPlayer().equals(p)) {
                return i;
            }
        }
        return -1;
    }
}
