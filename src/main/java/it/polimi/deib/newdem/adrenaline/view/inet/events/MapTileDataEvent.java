package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

import java.util.List;

public class MapTileDataEvent implements UserEvent {

    List<TilePosition> tileDataPosition;

    public MapTileDataEvent(List<TilePosition> tileDataPosition){
        this.tileDataPosition = tileDataPosition;
    }

    public List<TilePosition> getTileDataPosition() {
        return tileDataPosition;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.mapDidSendTileData(connection, this);
    }

}
