package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

import java.util.List;

public class MapSpawnPointDataEvent implements UserEvent {

    List<TilePosition> spawnPointTileDataPosition;

    public MapSpawnPointDataEvent(List<TilePosition> spawnPointTileDataPosition){
        this.spawnPointTileDataPosition = spawnPointTileDataPosition;
    }

    public List<TilePosition> getSpawnPointTileDataPosition() {
        return spawnPointTileDataPosition;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
