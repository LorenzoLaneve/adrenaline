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

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.GRAB;
import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.MOVE3;
import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.SHOOT;
import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.ChainsawUtils.CHAINSAW_ID;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.GREEN;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.MAGENTA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TargetingScopeInteractionTest {

    GameRigged game;

    @Before
    public void SetUp() throws Exception {
        game = TestingUtils.makeRiggedGame(MAGENTA, GREEN);
        game.setWeaponDeck(WeaponDeck.newPlayableDeckFromJson("fixed_decks/9_weapons.json"));
        game.setPowerupDeck(PowerUpDeck.fromJson("fixed_decks/6_scope_only_pups.json").createNewDeck());
    }

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

        // end extra damage
        sds.pushPupIndex(ScriptedDataSource.getUndoWeaponCardIndex()); // do not use other pups
        sds.pushAcceptPayment(); // extra damage resolves
        // push ok player, see below
        sds.pushNullPayment(); //sds.pushUndoPayment();
        // push ok player, see below
        sds.pushPupIndex(0); // select scope
        // push null player, see below
        sds.pushPupIndex(0); // select scope
        // now in extra damage

        // push opponent, see below
        sds.pushNullPayment(); // sds.pushUndoPayment(); // select 1dmg effect
        sds.pushWeaponCardIndex(0); // select chainsaw
        // p1t5

        // end p2t4
        // now with 3 scopes stacked
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
        assertEquals(1,opponents.size());
        assertNotEquals(t.getActivePlayer(), opponents.get(0));
        Player opponent = opponents.get(0);

        // end extra damage
        // sds.pushPupIndex(ScriptedDataSource.getNullPupIndex()); // do not use other pups
        // sds.pushAcceptPayment(); // extra damage resolves
        sds.pushPlayerFromMeta(opponent); // push ok player, see below
        // sds.pushUndoPayment();
        sds.pushPlayerFromMeta(opponent); // push ok player, see below
        // sds.pushPupIndex(0); // select scope
        sds.pushNullPlayer(); // sds.pushNullPlayer();  // push null player, see below
        // sds.pushPupIndex(0); // select scope
        // now in extra damage
        sds.pushPlayerFromMeta(opponent); // push target for chainsaw

        t = game.getNextTurn();
        t.bindDataSource(sds);
        t.execute();
        game.concludeTurn(t);

        assertEquals(2, opponent.getTotalDamage());
    }

}
