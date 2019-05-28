package it.polimi.deib.newdem.adrenaline.model.game.utils;

import it.polimi.deib.newdem.adrenaline.model.game.MockPlayer;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import org.junit.Test;

public class ScoreboardTest {

    @Test
    public void testGetPlacementIdentical() throws Exception {
        Scoreboard sb = new Scoreboard();

        Player p1 = new MockPlayer();
        Player p2 = new MockPlayer();

        sb.registerEntry(new ScoreboardEntry(p1, 3, 1));
        sb.registerEntry(new ScoreboardEntry(p2, 3, 1));
        sb.getPlacement(p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPlacementNegativeFirstNull() throws Exception {
        Scoreboard sb = new Scoreboard();

        sb.registerEntry(null);
        sb.registerEntry(new ScoreboardEntry(new MockPlayer(), 0,0));
        sb.getPlacement(new MockPlayer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPlacementNegativeLastNull() throws Exception {
        Scoreboard sb = new Scoreboard();

        sb.registerEntry(new ScoreboardEntry(new MockPlayer(), 0,0));
        sb.registerEntry(null);
        sb.getPlacement(new MockPlayer());
    }
}