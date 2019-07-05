package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.MapData;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * Event used to align the map data in the client with the server's model.
 * @see UserEvent to see what this class is used for.
 */
public class MapDataEvent implements UserEvent {

    private MapData data;


    public MapDataEvent(MapData data) {
        this.data = data;
    }

    public MapData getData() {
        return data;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
