package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.rigged.GameRigged;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpDeck;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponDeck;
import it.polimi.deib.newdem.adrenaline.model.map.TestingMapBuilder;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.*;
import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.ChainsawUtils.CHAINSAW_ID;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.GREEN;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.MAGENTA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Integration tests for a revenge interaction
 *
 * These tests are run as a simulation of a complete game with
 * controlled user inputs and cards drawn
 *
 * @see it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.SelectRevengePupInteraction
 */
public class RevengeInteractionTest {

    private GameRigged game;

    /**
     * Creates a new test game
     * @throws Exception
     */
    @Before
    public void SetUp() throws Exception {
        game = TestingUtils.makeRiggedGame(MAGENTA, GREEN);
        game.setWeaponDeck(WeaponDeck.newPlayableDeckFromJson("fixed_decks/9_weapons.json"));
        game.setPowerupDeck(PowerUpDeck.fromJson("fixed_decks/6_grenade_only_pups.json").createNewDeck());
    }

    /**
     * Simulates a game with a revenge interaction and undos at various steps
     * @throws Exception
     */
    @Test
    public void testUndos() throws Exception {
        ScriptedDataSource sds = new ScriptedDataSource( game,
                new ActionType(MOVE3, GRAB), // p1t1a1
                new ActionType(MOVE3),      // p1t1a2
                new ActionType(MOVE3, GRAB),//p2t2a1
                null, // end p2t2
                null, // end p1t3
                new ActionType(GRAB), // p2t4a1
                null, // end p2t4
                new ActionType(SHOOT), // p1t5
                null
        );

        Tile chainsawTile = ChainsawUtils.whereIsChainsaw(game.getMap());
        Tile dropTile = game.getMap().getTile(TestingMapBuilder.getDropTilePos());


        // end p1t5

        // end revenge
        sds.pushPupIndex(ScriptedDataSource.getNullPupIndex()); // do not select third revenge pup
        sds.pushPupIndex(0); // select second revenge pup
        sds.pushPupIndex(0); // select first revenge pup
        // now in revenge

        // push opponent, see below
        sds.pushNullPayment(); // select 1dmg effect
        sds.pushWeaponCardIndex(0); // select chainsaw
        // p1t5

        // end p2t4
        // now with 3 revenges stacked
        // grab, not interactive
        // p2t4

        // refill tiles
        // end p1t3
        // null

        // end p2t1
        // grab, not interactive
        sds.pushTile(dropTile);
        sds.pushPupIndex(0); // spawn

        // end p1t1
        sds.pushTile(dropTile); // go to drop tile
        sds.pushAcceptPayment(); //  pay for it
        sds.pushWeaponCardId(CHAINSAW_ID); // select chainsaw
        sds.pushTile(ChainsawUtils.whereIsChainsaw(game.getMap())); // p1t1a1 goto chainsaw
        sds.pushPupIndex(0); // p1t1spawn

        Turn t;


        t = game.getNextTurn();
        t.bindDataSource(sds);
        t.execute();
        game.concludeTurn(t);

        t = game.getNextTurn();
        t.bindDataSource(sds);
        t.execute();
        game.concludeTurn(t);

        t = game.getNextTurn();
        t.bindDataSource(sds);
        t.execute();
        game.concludeTurn(t);

        t = game.getNextTurn();
        t.bindDataSource(sds);
        t.execute();
        game.concludeTurn(t);

        List<Player> opponents = game.getPlayers();
        for(Player p : game.getPlayers()) {
            if(p == t.getActivePlayer()) {
                opponents.remove(p);
            }
        }
        Player opponent = opponents.get(0);

        assertEquals(1,opponents.size());
        assertNotEquals(t.getActivePlayer(), opponent);

        sds.pushPlayerFromMeta(opponents.get(0));

        t = game.getNextTurn();
        t.bindDataSource(sds);
        t.execute();
        game.concludeTurn(t);

        assertEquals(1, opponent.getMarksFromPlayer(t.getActivePlayer()));
    }

}
