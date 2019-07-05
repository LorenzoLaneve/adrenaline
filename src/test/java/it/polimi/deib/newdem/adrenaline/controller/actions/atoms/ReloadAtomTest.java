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
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Integration tests for {@code ReloadAtom}
 *
 * These tests are run as a simulation of a complete game with
 * controlled user inputs and cards drawn
 *
 * @see it.polimi.deib.newdem.adrenaline.controller.actions.atoms.ReloadAtom
 */
public class ReloadAtomTest {

    private Game game;
    private ScriptedDataSource source;

    /**
     * Builds a new test game
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        game = TestingUtils.makeTestGame(PlayerColor.MAGENTA);
        source = new ScriptedDataSource( game,
                new ActionType(AtomicActionType.MOVE3, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE3),
                new ActionType(AtomicActionType.MOVE1)
        );
    }

    /**
     * Simulates a game with a reload atom
     * @throws Exception
     */
    @Test
    public void testExecute() throws Exception {
        Turn turn = game.getNextTurn();
        turn.bindDataSource(source);
        turn.setAllowClosingPowerUps(false);

        source.pushPupIndex(0);
        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
        for(int i = 0; i < 10; i++) { source.pushWeaponCardIndex(0); }

        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED)); // move to red spawn
        source.pushPupIndex(0); // spawn // spawn at a random spawnpoint

        turn.execute();

        List<Weapon> loadedWeapons = turn.getActivePlayer().getInventory().getLoadedWeapons();
        loadedWeapons.get(0).discharge();

        refillAmmos(turn.getActivePlayer());
        game.concludeTurn(turn); // implies reload
    }

    private void refillAmmos(Player player) {
        player.getInventory().addAmmoSet(new AmmoSet(3,3,3));
    }
}