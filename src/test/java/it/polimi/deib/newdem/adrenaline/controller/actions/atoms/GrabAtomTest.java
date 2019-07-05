package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Integration tests for {@code GrabAtom}
 *
 * These tests are run as a simulation of a complete game with
 * controlled user inputs and cards drawn
 *
 * @see it.polimi.deib.newdem.adrenaline.controller.actions.atoms.GrabAtom
 */
public class GrabAtomTest {

    private Game game;
    private ScriptedDataSource source;

    /**
     * Creates a new test game
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        game = TestingUtils.makeTestGame(PlayerColor.MAGENTA);
        source = new ScriptedDataSource( game,
                new ActionType(AtomicActionType.MOVE3, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE2, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE2, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE2, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE2, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE2, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE2, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE2, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE2, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE2, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE2, AtomicActionType.GRAB)
                );
    }

    /**
     * Simulates a game with many grabs of weapons and undos of weapon selection
     * @throws Exception
     */
    @Test
    public void testGrabUndoNNewCardSelection() throws Exception {
        source.pushWeaponCardIndex(0);
        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
        source.pushWeaponCardIndex(0);
        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));

        for(int i = 0; i < 20; i++) {
            source.pushWeaponCardIndex(ScriptedDataSource.getUndoWeaponCardIndex());
            source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
        }


        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED)); // move to red spawnpoint tile
        source.pushPupIndex(0); // spawn // spawn at a random spawnpoint

        Turn turn = game.getNextTurn();
        turn.bindDataSource(source);
        refillAmmos(turn.getActivePlayer());
        turn.setRunClosingActions(false);
        turn.execute();
        game.concludeTurn(turn);

        assertEquals(0, source.getWeaponCardLeftovers());
    }

    /**
     * Simulates a game with many grabs of weapons and undos of payment of such weapons
     * @throws Exception
     */
    @Test
    public void testGrabUndoNPayments() throws Exception {
        // i want to try to start an attempt to grab, then undo and redo new weapon selection n > 10 times

        Turn turn = game.getNextTurn();
        turn.bindDataSource(source);

        for(int i = 0; i < 50; i++) {
            if(i % 2 == 0) { source.pushUndoPayment(); }
            source.pushWeaponCardIndex(0);
            source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
        }

        // ^ ACTION 2
        // do not discard card
        // pay for it
        // buy random weapon from spawnpoint
        refillAmmos(turn.getActivePlayer());
        // ^ fill inventory with max ammos
        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED)); // move to red spawnpoint tile
        source.pushPupIndex(0); // spawn // spawn at a random spawnpoint

        turn.setRunClosingActions(false);
        turn.execute();
        game.concludeTurn(turn);

        for(int i = 0; i < 4; i++) {
            turn = game.getNextTurn();
            turn.bindDataSource(source);
            turn.setAllowClosingPowerUps(false);
            turn.execute();
            game.concludeTurn(turn);
        }
    }

    private void refillAmmos(Player player) {
        player.getInventory().addAmmoSet(new AmmoSet(3,3,3));
    }
}