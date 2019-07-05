package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.rigged.GameRigged;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.*;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.*;
import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.GRAB;
import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.MOVE3;
import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.ChainsawUtils.CHAINSAW_ID;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.GREEN;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.MAGENTA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Integration tests for {@code DischargeInteraction}
 *
 * These tests are run as a simulation of a complete game with
 * controlled user inputs and cards drawn
 *
 * @see it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.DischargeInteraction
 */
public class DischargeInteractionTest {

    private GameRigged game;

    /**
     * Creates a tesdt game with controlled weapons and powerups
     * @throws Exception
     */
    @Before
    public void SetUp() throws Exception {
        game = TestingUtils.makeRiggedGame(MAGENTA, GREEN);
        game.setWeaponDeck(WeaponDeck.newPlayableDeckFromJson("fixed_decks/9_weapons.json"));
        game.setPowerupDeck(PowerUpDeck.fromJson("fixed_decks/11_call_only_pups.json").createNewDeck());
    }

    /**
     * Simulates a game with a discharge weapon interaction
     * @throws Exception
     */
    @Test
    public void testUndos() throws Exception {
        ScriptedDataSource sds = new ScriptedDataSource( game,
                new ActionType(MOVE3, GRAB), // p1a1
                null,         // p1a2 (end turn)
                new ActionType(MOVE3),
                null,
                new ActionType(SHOOT),
                new ActionType(MOVE1),// p2a1 self move
                new ActionType(RELOAD)
                // reload
        );

        Tile chainsawTile = ChainsawUtils.whereIsChainsaw(game.getMap());

        // see below for reload and payments
        // stay still, termiante turn gracefully
        sds.pushTile(chainsawTile);
        // bind red
        // before turn
        // refuse fragment implicit
        // before turn sds.pushUndoPayment();
        // choose weapon
        sds.pushWeaponCardId(CHAINSAW_ID);
        // shoot
        // p1t2

        // end turn for null action
        // get ready to take hit
        // move
        sds.pushTile(chainsawTile);
        //spawn
        sds.pushPupIndex(0);
        //p2t1

        // end p1t1
        // end action 1
        // auto pay ok
        // grab chainsaw
        sds.pushWeaponCardId(CHAINSAW_ID);
        // move to chainsawTile
        sds.pushTile(chainsawTile);
        sds.pushPupIndex(0); // spawn

        Turn t = game.getNextTurn();
        t.setRunClosingActions(false);
        t.bindDataSource(sds);
        t.execute();
        game.concludeTurn(t);

        t = game.getNextTurn();
        t.setAllowClosingPowerUps(false);
        // allow reload
        t.bindDataSource(sds);
        t.execute();
        game.concludeTurn(t);

        t = game.getNextTurn();

        // auto pay ok
        // try again
        sds.pushWeaponCardId(CHAINSAW_ID);
        // reject first payment
        sds.pushNullPayment(); // sds.pushUndoPayment();
        // select chainsaw for reload
        sds.pushWeaponCardId(CHAINSAW_ID);
        // red meta binds

        List<Player> playes = game.getPlayers();
        playes.remove(t.getActivePlayer());
        Player opponent = playes.get(0);
        assertEquals(1, playes.size());

        // reload
        sds.pushPlayerFromMeta(playes.get(0));
        // undo payment
        sds.pushNullPayment(); // sds.pushUndoPayment();

        t.setAllowClosingPowerUps(false);
        t.bindDataSource(sds);
        t.execute();
        game.concludeTurn(t);

        assertEquals(1,opponent.getDamageFromPlayer(t.getActivePlayer()));
        assertEquals(1, t.getActivePlayer().getInventory().getLoadedWeapons().size());
        assertEquals(CHAINSAW_ID, t.getActivePlayer().getInventory().getLoadedWeapons().get(0).getCard().getCardID());
    }
}