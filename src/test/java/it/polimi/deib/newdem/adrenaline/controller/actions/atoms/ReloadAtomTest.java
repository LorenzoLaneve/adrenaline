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
import it.polimi.deib.newdem.adrenaline.model.map.TestingMapBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ReloadAtomTest {

    private Game game;
    private ScriptedDataSource source;

    @Before
    public void setUp() throws Exception {
        game = TestingUtils.makeTestGame(PlayerColor.MAGENTA);
        source = new ScriptedDataSource(
                new ActionType(AtomicActionType.MOVE3, AtomicActionType.GRAB),
                new ActionType(AtomicActionType.MOVE3),
                new ActionType(AtomicActionType.MOVE1)
        );
    }

    @Test
    public void testExecute() throws Exception {
        // FIXME non determinism
        /*
        Turn turn = game.getNextTurn();
        turn.bindDataSource(source);

        source.pushPupIndex(0);
        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
        for(int i = 0; i < 10; i++) { source.pushWeaponCardIndex(0); }

        // pickUpWeaponHere();
        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED)); // move to red spawn
        source.pushPupIndex(0); // spawn // spawn at a random spawnpoint

        turn.execute();

        List<Weapon> loadedWeapons = turn.getActivePlayer().getInventory().getLoadedWeapons();
        loadedWeapons.get(0).discharge();

        refillAmmos(turn.getActivePlayer());
        game.concludeTurn(turn); // implies reload
        */
    }

    private void pickUpWeaponHere() {
        // do not discard card
        // pay for it
        // buy random weapon from spawnpoint
        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED)); // move to red spawnpoint tile
        // ACTION 1
    }

    private void moveStill(Player player) {
        source.pushTile(player.getTile());
    }

    private void refillAmmos(Player player) {
        player.getInventory().addAmmoSet(new AmmoSet(3,3,3));
    }
}