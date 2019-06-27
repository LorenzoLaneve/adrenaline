package it.polimi.deib.newdem.adrenaline.model.game.action_board;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.game.MockPlayer;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualActionBoardView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualGameView;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.*;
import static org.junit.Assert.*;

public class ActionBoardImplTest {

    private ActionBoardImpl cab;

    @Before
    public void setUp() {
        cab = new ActionBoardImpl();
    }

    @Test
    public void testSetListener() {
        VirtualGameView vgv = new VirtualGameView();
        cab.setListener(new VirtualActionBoardView(vgv, (new MockPlayer()).getColor()));
    }

    @Test
    public void testGetBasicActions() {
        List<ActionFactory> factories = cab.getBasicActions();

        ActionType t1 = new ActionType(MOVE3);
        ActionType t2 = new ActionType(MOVE1, GRAB);
        ActionType t3 = new ActionType(SHOOT);

        assertEquals(t1, factories.get(0).getType());
        assertEquals(t2, factories.get(1).getType());
        assertEquals(t3, factories.get(2).getType());
        assertEquals(3, factories.size());
    }

    @Test
    public void testGetIterations() throws Exception {
        assertEquals(2, cab.getIterations());
    }

    @Test
    public void testGoFrenzy() throws Exception {
        VirtualGameView vgv = new VirtualGameView();
        cab.goFrenzy(false);
        cab = new ActionBoardImpl();
        cab.setListener(new VirtualActionBoardView(vgv, (new MockPlayer()).getColor()));
        cab.goFrenzy(true);
    }

    @Test(expected = IllegalStateException.class)
    public void testGoFrenzyNegativeDouble() throws Exception {
        cab.goFrenzy(true);
        cab.goFrenzy(false);
    }
}