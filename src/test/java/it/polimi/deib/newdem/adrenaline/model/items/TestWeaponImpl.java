package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.model.game.ColorUserPair;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.SpawnPointTile;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.server.NullVirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualDamageBoardView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualKillTrackView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestWeaponImpl {

    private Player p1;
    private Game game;
    private VirtualGameView vgv;
    private Weapon weapon;
    private Map map;

    @Before
    public void setUp() throws Exception {
        TestingUtils.loadSingleton();

        map =  Map.createMap("Map0_0.json");

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
        TestingUtils.loadSingleton();

        game = new GameImpl(gp);

        VirtualGameView vgv = new NullVirtualGameView();
        game.setGameListener(vgv);
        game.setKillTrackListener(new VirtualKillTrackView(vgv)); //???

        game.init();

        p1 = game.getPlayerFromColor(PlayerColor.YELLOW);

        p1.getDamageBoard().setListener(new VirtualDamageBoardView(p1, vgv));

        SpawnPointTile redSpawnPoint = (SpawnPointTile) game.getMap().getSpawnPointFromColor(AmmoColor.RED);

        List<WeaponCard> weaponCards = redSpawnPoint.inspectWeaponSet().getWeapons();

        weapon = new WeaponImpl(redSpawnPoint.grabWeapon(weaponCards.get(0)), p1);

        p1.getInventory().addWeapon(weapon);
    }

    @Test
    public void getCard() {
        weapon.getCard();
    }

    @Test
    public void isLoaded() {
        weapon.isLoaded();
    }

    @Test
    public void call() {
        weapon.discharge();
        assertFalse(weapon.isLoaded());
    }

    @Test
    public void reload() {
        weapon.discharge();
        assertFalse(weapon.isLoaded());
        weapon.reload();
        assertTrue(weapon.isLoaded());
    }

    @Test
    public void returnOwner() {
        assertEquals(p1, weapon.getOwner());
    }
}