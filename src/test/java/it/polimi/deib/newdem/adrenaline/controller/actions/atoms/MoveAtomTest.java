package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.game.ColorUserPair;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.map.OrdinaryTile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MoveAtomTest {

    Game game;
    ScriptedDataSource source;

    @Before
    public void setUp() throws Exception {
        game = TestingUtils.makeTestGame(PlayerColor.MAGENTA);
        source = new ScriptedDataSource( game,
                new ActionType(AtomicActionType.MOVE1),
                new ActionType(AtomicActionType.MOVE4),
                new ActionType(AtomicActionType.MOVE4));
        source.pushPupIndex(0);
        source.pushPupIndex(ScriptedDataSource.getUndoPupIndex());
        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
        source.pushTile(ScriptedDataSource.getUndoTile());
        source.pushTile(game.getMap().getTile(new TilePosition(1,0)));
    }

    @Test
    public void testExecute() throws Exception {
        Turn turn = game.getNextTurn();
        turn.setRunClosingActions(false);
        turn.bindDataSource(source);
        turn.execute();

    }
}