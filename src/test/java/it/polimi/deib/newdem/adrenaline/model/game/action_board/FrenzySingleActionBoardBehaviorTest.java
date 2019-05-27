package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrenzySingleActionBoardBehaviorTest {

    private ActionBoard ab;

    @Before
    public void setUp() {
        ab = new ActionBoardImpl();
        ab.goFrenzy(false);
    }

    @Test
    public void testGetIterations() {
        assertEquals(1, ab.getIterations());
    }

    @Test
    public void testOnLeave() {
        (new FrenzySingleActionBoardBehavior()).onLeave((ActionBoardImpl) ab);
    }
}