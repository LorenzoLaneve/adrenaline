package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.Config;
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

public class TestGameImpl {

    Game game;
    Map map;
    Player player1;
    Player player2;
    Player player3;

    @Before
    public void setUp(){
        map =  Map.createMap(this.getClass().getClassLoader().getResource("Map0_0.json").getFile().replace("%20", " "));

        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());

        ColorUserPair colorUserPair1 = new ColorUserPair(PlayerColor.YELLOW, new User());
        ColorUserPair colorUserPair2 = new ColorUserPair(PlayerColor.GREEN, new User());
        ColorUserPair colorUserPair3 = new ColorUserPair(PlayerColor.GRAY, new User());
        List<ColorUserPair> listPairs = new ArrayList<>();
        listPairs.add(colorUserPair1);
        listPairs.add(colorUserPair2);
        listPairs.add(colorUserPair3);

        gp.setColorUserOrder(listPairs);

        gp.setGameMap(map);

        game = new GameImpl(gp);


    }

    @Test
    public void init(){

        game.init();
    }

}