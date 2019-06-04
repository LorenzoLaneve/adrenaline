package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class SpawnPlayerEvent implements UserEvent {

    PlayerColor playerColor;
    TilePosition spawnPoint;

    public SpawnPlayerEvent(PlayerColor playerColor, TilePosition tilePosition){
        this.playerColor = playerColor;
        this.spawnPoint = tilePosition;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public TilePosition getSpawnPoint() {
        return spawnPoint;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidSpawn(connection, this);
    }

}
