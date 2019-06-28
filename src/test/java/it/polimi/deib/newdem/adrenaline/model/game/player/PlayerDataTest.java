package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.model.game.*;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.TestingMapBuilder;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.server.NullVirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualDamageBoardView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualKillTrackView;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerDataTest {

    private Game game;
    private Player player;
    private PlayerData playerData;
    private PowerUpCard pup;
    private Weapon w1;
    private Weapon w2;

    @Before
    public void setUp() throws Exception {
        TestingUtils.loadSingleton();
        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());
        gp.setGameMap(TestingMapBuilder.getNewMap(this.getClass()));
        gp.setMinPlayers(2);
        gp.setColorUserOrder(Arrays.asList(
                new ColorUserPair(PlayerColor.MAGENTA, new User()),
                new ColorUserPair(PlayerColor.GREEN, new User())
        ));
        game = new GameImpl(gp);


        VirtualGameView vgv = new NullVirtualGameView();
        game.setGameListener(vgv);
        game.setKillTrackListener(new VirtualKillTrackView(vgv)); //???

        game.init();

        player = game.getPlayerFromColor(PlayerColor.MAGENTA);
        PlayerInventory inventory = player.getInventory();
        inventory.addAmmo(AmmoColor.RED, 1);
        inventory.addAmmo(AmmoColor.BLUE, 1);
        inventory.addAmmo(AmmoColor.YELLOW, 1);
        w1 = new MockWeapon("Gun", true);
        inventory.addWeapon(w1);
        w2 = new MockWeapon("Knife", false);
        inventory.addWeapon(w2);
        pup = new MockPowerUpCard();
        inventory.addPowerUp(pup);
        player.getDamageBoard().setListener(new VirtualDamageBoardView(player, vgv));
        player.getDamageBoard().appendDamage(game.getPlayerFromColor(PlayerColor.GREEN));
        player.getDamageBoard().setMarksFromPlayer(1, game.getPlayerFromColor(PlayerColor.GREEN));
        playerData = player.generatePlayerData();
    }

    @Test
    public void testGetPowerUpCards() throws Exception {
        assertEquals(playerData.getPowerUpCards().get(0), (Integer) pup.getCardID());
    }

    @Test
    public void testGetReadyWeaponCards() throws Exception {
        assertEquals(playerData.getReadyWeaponCards().get(0), (Integer) w1.getCard().getCardID());
    }

    @Test
    public void testGetUnloadedWeaponCards() throws Exception {
        assertEquals(playerData.getUnloadedWeaponCards().get(0), (Integer) w2.getCard().getCardID());
    }

    @Test
    public void testGetAmmos() throws Exception {
        Integer[] arr = {1,1,1};
        assertArrayEquals(playerData.getAmmos().values().toArray(), arr);
    }

    @Test
    public void testIsActionBoardFrenzy() throws Exception {
        assertFalse(playerData.isActionBoardFrenzy());
    }

    @Test
    public void testIsDamageBoardFrenzy() throws Exception {
        assertFalse(playerData.isDamageBoardFrenzy());
    }

    @Test
    public void testGetDamages() throws Exception {
        assertEquals(PlayerColor.GREEN, playerData.getDamages().get(0));
    }

    @Test
    public void testGetMarks() throws Exception {
        assertEquals((Integer) 1 , playerData.getMarks().get(PlayerColor.GREEN));
    }

    @Test
    public void testGetColor() throws Exception {
        assertEquals(PlayerColor.MAGENTA, playerData.getColor());
    }

    @Test
    public void testGetPosition() throws Exception {
        assertEquals(playerData.getPosition(), new TilePosition(0,0));
        player.setTile(game.getMap().getTile(new TilePosition(1,0)));
        assertEquals(new TilePosition(1,0), player.generatePlayerData().getPosition());
    }

    @Test
    public void testGetDeaths() throws Exception {
        assertEquals(0, playerData.getDeaths());
    }

    @Test
    public void testIsDead() throws Exception {
        assertFalse(playerData.isDead());
    }

    @Test
    public void testHasFirstPlayerCard() throws Exception {
        playerData.hasFirstPlayerCard();
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(0, playerData.getScore());
    }
}