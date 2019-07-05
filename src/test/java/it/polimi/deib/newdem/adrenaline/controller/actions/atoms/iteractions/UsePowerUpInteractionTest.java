package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.rigged.GameRigged;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.DropDeck;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpDeck;
import it.polimi.deib.newdem.adrenaline.model.map.TestingMapBuilder;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Integration tests for a use powerup interaction
 *
 * These tests are run as a simulation of a complete game with
 * controlled user inputs and cards drawn
 *
 * @see it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.SelectPupInteraction
 */
public class UsePowerUpInteractionTest {

    private GameRigged game;

    /**
     * Creates a new test game
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        game = TestingUtils.makeRiggedGame(PlayerColor.MAGENTA, PlayerColor.GREEN);
        game.setPowerupDeck(PowerUpDeck.fromJson("fixed_decks/6_teleport_only_pups.json").createNewDeck());
        game.setDropDeck(DropDeck.fromJson("fixed_decks/18_pup_all_drops.json").createNewDeck());
    }

    /**
     * Simulates a game with a powerup interaction and undos at various steps
     * @throws Exception
     */
    @Test
    public void testUndo() throws Exception {

        ScriptedDataSource sds = new ScriptedDataSource( game,
                new ActionType(MOVE3, GRAB),
                new ActionType(MOVE1),
                new ActionType(MOVE3, GRAB, USE_POWERUP),
                new ActionType(MOVE1),
                new ActionType(MOVE1)
                );

        Tile dropTile = game.getMap().getTile(TestingMapBuilder.getDropTilePos());
        Tile redSpawn = game.getMap().getSpawnPointFromColor(AmmoColor.RED);

        sds.pushTile(dropTile); // mvoe to gracefully conclude turn
        sds.pushPupIndex(ScriptedDataSource.getNullPupIndex()); // do not use tepeorts at EOT
        sds.pushPupIndex(ScriptedDataSource.getNullPupIndex()); // do not use other teleports this turn
        sds.pushTile(dropTile); // use teleport, 2 pup in inv.
        sds.pushPupIndex(0); // try again
        sds.pushTile(ScriptedDataSource.getUndoTile()); // undo tile selection
        sds.pushPupIndex(0); // try to use one teleport
        // PUP action during turn
        // implicit grab, 3 pup in inv
        sds.pushTile(dropTile);

        // EOT, refill tile
        sds.pushPupIndex(ScriptedDataSource.getNullPupIndex());
        sds.pushTile(redSpawn);
        sds.pushPupIndex(0);
        sds.pushTile(ScriptedDataSource.getUndoTile());
        sds.pushPupIndex(0); // EOT pups, attempt to teleport
        sds.pushTile(dropTile); // move again to gracefully conclude turn
        // implicit grab of teleport from drop, 2 pup in inv.
        sds.pushTile(dropTile); // move
        sds.pushPupIndex(0); // spawn, 1 pup in inv.

        Turn t = game.getNextTurn();
        t.bindDataSource(sds);
        t.execute();

        assertEquals(redSpawn, t.getActivePlayer().getTile());

        t = game.getNextTurn();
        t.bindDataSource(sds);
        t.execute();

        assertEquals(dropTile, t.getActivePlayer().getTile());

    }
}
