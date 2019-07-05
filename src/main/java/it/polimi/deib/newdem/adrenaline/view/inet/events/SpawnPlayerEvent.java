package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The given player spawned at the given location in map.
 * @see UserEvent to see what this class is used for.
 */
public class SpawnPlayerEvent implements UserEvent {

    private PlayerColor playerColor;
    private TilePosition spawnPoint;

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
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
