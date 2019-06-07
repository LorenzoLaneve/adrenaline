package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;

public class SpawnWeaponEvent implements UserEvent {

    private int cardID;
    private TilePosition tilePosition;

    public SpawnWeaponEvent(TilePosition tilePosition, int cardID) {
        this.cardID = cardID;

        this.tilePosition = getTilePosition();
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
