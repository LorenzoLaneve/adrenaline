package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class DespawnDropEvent implements UserEvent {

    private TilePosition tilePosition;

    public DespawnDropEvent(TilePosition tile) {
        this.tilePosition = tile;
    }

    public TilePosition getTilePosition() {
        return tilePosition;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
