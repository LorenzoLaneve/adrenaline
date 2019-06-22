package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.OrdinaryTile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGameData {

    GameData gameData;

    @Before
    public void setUp() throws Exception {
        gameData = new GameData();
    }

    @Test
    public void addPlayer() {
        gameData.addPlayer("test", PlayerColor.GRAY);
    }

    @Test
    public void setMapID() {
        gameData.setMapID("Map0");
    }

    @Test
    public void addDrop() {
        gameData.addDrop(new TilePosition(1,1), GameData.DropType.BLUE_AMMO, GameData.DropType.BLUE_AMMO, GameData.DropType.POWER_UP);
    }

    @Test
    public void setPlayerLocation() {
        gameData.setPlayerLocation(PlayerColor.GRAY, new TilePosition(1,1));
    }

    @Test
    public void setNextTurnPlayer() {
        gameData.setNextTurnPlayer(PlayerColor.GRAY);
    }
}