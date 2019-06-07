package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

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
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
