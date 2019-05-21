package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

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
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.mapDidSendSpawnPointData(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendMapSpawnPointDataEvent(this);
    }
}
