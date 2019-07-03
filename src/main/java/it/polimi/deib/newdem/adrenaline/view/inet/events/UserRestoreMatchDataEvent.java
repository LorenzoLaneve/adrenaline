package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerData;
import it.polimi.deib.newdem.adrenaline.model.map.MapData;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.List;

public class UserRestoreMatchDataEvent implements UserEvent{

    private GameData gameData;
    private MapData mapData;
    private List<PlayerData> playerDataList;
    private PlayerColor playerColor;

    public UserRestoreMatchDataEvent(PlayerColor playerColor, GameData gameData, MapData mapData, List<PlayerData> playerDataList){
        this.playerColor = playerColor;
        this.gameData = gameData;
        this.playerDataList = playerDataList;
        this.mapData = mapData;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }



}
