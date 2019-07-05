package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

/**
 * The weapon with the given card ID spawned in the given tile.
 * @see UserEvent to see what this class is used for.
 */
public class SpawnWeaponEvent implements UserEvent {

    private int cardID;
    private TilePosition tilePosition;

    public SpawnWeaponEvent(TilePosition tilePosition, int cardID) {
        this.cardID = cardID;

        this.tilePosition = tilePosition;
    }

    public int getWeaponCardID() {
        return cardID;
    }

    public TilePosition getTilePosition() {
        return tilePosition;
    }


    @Override
    public void publish(UserConnection connection) {
        connection.publishEvent(this);
    }

}
