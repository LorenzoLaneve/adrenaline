package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.game.rigged.GameRigged;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.map.TestingMapBuilder;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType.*;
import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.MAGENTA;
import static org.junit.Assert.*;

public class ShootInteractionTruncatedTest {

        GameRigged game;

        @Before
        public void SetUp() throws Exception {
            game = TestingUtils.makeRiggedGame(MAGENTA);
        }

        @Test
        public void testExecute() throws Exception {
            ScriptedDataSource sds = new ScriptedDataSource(
                    new ActionType(MOVE3, GRAB),
                    new ActionType(MOVE3, SHOOT),
                    new ActionType(MOVE3)
            );

            // select weapon, undo
            sds.pushWeaponCardIndex(0);
            // move to position
            sds.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
            // end action 1
            // grab weapon
            sds.pushWeaponCardIndex(0);
            // move to spawn
            sds.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
            sds.pushPupIndex(0);

            Turn t = game.getNextTurn();
            t.setRunClosingActions(false);
            t.bindDataSource(sds);
            t.execute();
        }

        @Test
        public void testUndos() throws Exception {
            ScriptedDataSource sds = new ScriptedDataSource(
                    new ActionType(MOVE2, GRAB),
                    new ActionType(MOVE3)
            );

            sds.pushWeaponCardIndex(0);
            sds.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
            // end action 1
            // pick up weapon
            sds.pushWeaponCardIndex(0);
            sds.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.BLUE));
            // undo, back to tile
            sds.pushWeaponCardIndex(ScriptedDataSource.getUndoWeaponCardIndex());
            // attempt to grab weapon
            sds.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
            sds.pushPupIndex(0);

            Turn t = game.getNextTurn();
            t.setRunClosingActions(false);
            t.bindDataSource(sds);
            t.execute();
        }
    }