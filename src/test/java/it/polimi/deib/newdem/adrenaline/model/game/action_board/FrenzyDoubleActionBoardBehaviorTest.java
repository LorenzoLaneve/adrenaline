package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import org.junit.Test;

import static org.junit.Assert.*;

public class FrenzyDoubleActionBoardBehaviorTest {

    @Test
    public void testGetIterations() {
        ActionBoard ab = new ActionBoardImpl();
        ab.goFrenzy(true);
        assertEquals(2, ab.getIterations());
    }
}