package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import org.junit.Test;

public class OrdinaryActionBoardBehaviorTest {

    @Test
    public void testOnEnter() {
        (new OrdinaryActionBoardBehavior()).onEnter(new ActionBoardImpl());
    }
}