package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.model.game.ColorUserPair;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestIntersectTileSelector {
    Map map;
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Game game;

    @Before
    public void setUp() throws Exception {

        map =  Map.createMap(this.getClass().getClassLoader().getResource("Map0_0.json").getFile().replace("%20", " "));

        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());

        ColorUserPair colorUserPair1 = new ColorUserPair(PlayerColor.YELLOW, new User());
        ColorUserPair colorUserPair2 = new ColorUserPair(PlayerColor.GREEN, new User());
        ColorUserPair colorUserPair3 = new ColorUserPair(PlayerColor.GRAY, new User());
        ColorUserPair colorUserPair4 = new ColorUserPair(PlayerColor.MAGENTA, new User());
        List<ColorUserPair> listPairs = new ArrayList<>();
        listPairs.add(colorUserPair1);
        listPairs.add(colorUserPair2);
        listPairs.add(colorUserPair3);
        listPairs.add(colorUserPair4);


        gp.setColorUserOrder(listPairs);

        gp.setGameMap(map);

        game = new GameImpl(gp);

        game.init();

        player1 = game.getPlayerFromColor(PlayerColor.YELLOW);
        player2 = game.getPlayerFromColor(PlayerColor.GREEN);
        player3 = game.getPlayerFromColor(PlayerColor.GRAY);
        player4 = game.getPlayerFromColor(PlayerColor.MAGENTA);

        player1.getInventory().addAmmo(AmmoColor.RED, 1);
        player2.getInventory().addAmmo(AmmoColor.BLUE, 1);
        player3.getInventory().addAmmo(AmmoColor.BLUE, 1);
        player4.getInventory().addAmmo(AmmoColor.BLUE,1);


        TilePosition tilePosition1 = new TilePosition(0,0);
        TilePosition tilePosition2 = new TilePosition(1,0);
        TilePosition tilePosition3 = new TilePosition(2,1);
        TilePosition tilePosition4 = new TilePosition(0,1);

        Tile destination1 = map.getTile(tilePosition1);
        Tile destination2 = map.getTile(tilePosition2);
        Tile destination3 = map.getTile(tilePosition3);
        Tile destination4 = map.getTile(tilePosition4);


        map.movePlayer(player1, destination1);
        map.movePlayer(player2, destination2);
        map.movePlayer(player3, destination3);
        map.movePlayer(player4,destination4);
    }

    @Test
    public void isSelectable() {

        NearTileSelector nearTileSelector = new NearTileSelector(player1, 0,2);
        VisibleTileSelector visibleTileSelector = new VisibleTileSelector(player1.getTile());

        IntersectTileSelector intersectTileSelector = new IntersectTileSelector(nearTileSelector, visibleTileSelector);

        assertTrue(intersectTileSelector.isSelectable(map, player2.getTile()));
        assertFalse(intersectTileSelector.isSelectable(map, player3.getTile()));

    }
}