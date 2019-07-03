package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.rigged.GameRigged;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponDeck;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.*;
import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.GRAB;
import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.MOVE3;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.GREEN;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.MAGENTA;
import static org.junit.Assert.assertEquals;


public class DischargeInteractionTest {

    GameRigged game;

    private static final int CHAINSAw_ID = 22;

    @Before
    public void SetUp() throws Exception {
        game = TestingUtils.makeRiggedGame(MAGENTA, GREEN);
        game.setWeaponDeck(WeaponDeck.newPlayableDeckFromJson("fixed_decks/TestDischarge/weapons.json"));
    }

    @Test
    public void testExecute() throws Exception {
        /*
        ScriptedDataSource sds = new ScriptedDataSource(
                new ActionType(MOVE3, GRAB), // p1a1
                null,         // p1a2 (end turn)
                new ActionType(MOVE3),
                null,
                new ActionType(SHOOT)// p2a1 (end turn)
        );

        // done.
        sds.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.BLUE));
        // start action 3
        // end action 2
        // then undo move too, killing the current action
        sds.pushTile(ScriptedDataSource.getUndoTile());
        // at weapon selection screen, undo from weapon selection
        sds.pushWeaponCardIndex(ScriptedDataSource.getUndoWeaponCardIndex());
        // move to position
        sds.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
        // end action 1
        // STDS automates payment
        // grab random weapon
        sds.pushWeaponCardIndex(0);
        // move to spawn
        sds.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
        sds.pushPupIndex(0);

        Turn t = game.getNextTurn();
        t.setRunClosingActions(false);
        t.bindDataSource(sds);
        t.execute();

        assertEquals(
                game.getMap().getSpawnPointFromColor(AmmoColor.BLUE).getPosition(),
                t.getActivePlayer().getTile().getPosition());
        */
    }

    @Test
    public void testUndos() throws Exception {
        ScriptedDataSource sds = new ScriptedDataSource(
                new ActionType(MOVE3, GRAB), // p1a1
                null,         // p1a2 (end turn)
                new ActionType(MOVE3),
                null,
                new ActionType(SHOOT),
                new ActionType(MOVE1),// p2a1 self move
                new ActionType(RELOAD)
                // reload
        );

        Tile chainsawTile = whereIsChainsaw(game.getMap());

        // see below for reload and payments
        // stay still, termiante turn gracefully
        sds.pushTile(chainsawTile);
        // bind red
        // before turn
        // refuse fragment implicit
        // before turn sds.pushUndoPayment();
        // choose weapon
        sds.pushWeaponCardId(CHAINSAw_ID);
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
        sds.pushWeaponCardId(CHAINSAw_ID);
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
        sds.pushWeaponCardId(CHAINSAw_ID);
        // reject first payment
        sds.pushUndoPayment();
        // select chainsaw for reload
        sds.pushWeaponCardId(CHAINSAw_ID);
        // red meta binds
        List<Player> playes = game.getPlayers();
        playes.remove(t.getActivePlayer());
        assertEquals(1, playes.size());
        sds.pushPlayerFromMeta(playes.get(0));
        // undo payment
        sds.pushUndoPayment();
        t.setRunClosingActions(false);
        t.bindDataSource(sds);
        t.execute();
        game.concludeTurn(t);
    }

    private Tile whereIsChainsaw(Map map) {
        for(AmmoColor color : AmmoColor.values()) {
            Tile spawn = map.getSpawnPointFromColor(color);
            for (WeaponCard wc : spawn.inspectWeaponSet().getWeapons()) {
                if (wc.getCardID() == CHAINSAw_ID) {
                    return spawn;
                }
            }
        }
        throw new IllegalStateException();
    }
}