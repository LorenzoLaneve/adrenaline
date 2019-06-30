package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.ScriptedDataSource;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import org.junit.Before;

import static org.junit.Assert.*;

public class GrabAtomTest {

    private Game game;
    private ScriptedDataSource source;

    @Before
    public void setUp() throws Exception {
        game = TestingUtils.makeTestGame(PlayerColor.MAGENTA);
        source = new ScriptedDataSource(
                new ActionType(AtomicActionType.MOVE1, AtomicActionType.GRAB)
        );
        source.pushPupIndex(0); // spawn
        source.pushTile(game.getMap().getSpawnPointFromColor(AmmoColor.RED));
        source.pushTile(ScriptedDataSource.getUndoTile());
        source.pushTile(game.getMap().getTile(new TilePosition(1,0)));
    }
}