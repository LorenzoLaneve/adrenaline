package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The given weapon despawned from the given tile.
 * @see UserEvent to see what this class is used for.
 */
public class DespawnWeaponEvent implements UserEvent {

    private TilePosition tilePosition;
    private int cardId;

    public DespawnWeaponEvent(TilePosition tilePosition, int cardId) {
        this.tilePosition = tilePosition;
        this.cardId = cardId;
    }

    public TilePosition getTilePosition() {
        return tilePosition;
    }

    public int getCardID() {
        return cardId;
    }

    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }
}
