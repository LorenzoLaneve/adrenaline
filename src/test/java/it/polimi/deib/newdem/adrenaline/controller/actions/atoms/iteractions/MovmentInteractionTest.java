package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.rigged.GameRigged;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.MOVE3;
import static org.junit.Assert.*;

/**
 * Integration tests for a movement interaction
 *
 * These tests are run as a simulation of a complete game with
 * controlled user inputs and cards drawn
 *
 * @see it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.MovmentInteraction
 */
public class MovmentInteractionTest {

    private GameRigged gameRigged;
    private ScriptedDataSource sds;

    /**
     * Creates a new test game
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        gameRigged = TestingUtils.makeRiggedGame(PlayerColor.MAGENTA);
        sds = new ScriptedDataSource( gameRigged,
                new ActionType(MOVE3),
                new ActionType(MOVE3));
        sds.pushTile(gameRigged.getMap().getSpawnPointFromColor(AmmoColor.RED));
        sds.pushTile(gameRigged.getMap().getSpawnPointFromColor(AmmoColor.RED));
        sds.pushPupIndex(0); // spawn
    }

    /**
     * Simulates a game with movement interaction
     * @throws Exception
     */
    @Test
    public void testExecute() throws Exception {
        Turn t = gameRigged.getNextTurn();
        t.bindDataSource(sds);
        t.setRunClosingActions(false);
        t.execute(); // move
        assertEquals(gameRigged.getMap().getSpawnPointFromColor(AmmoColor.RED),
                gameRigged.getPlayerFromColor(PlayerColor.MAGENTA).getTile());
    }
}