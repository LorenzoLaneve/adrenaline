package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The drops in the given tile despawned from the map.
 * @see UserEvent to see what this class is used for.
 */
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
