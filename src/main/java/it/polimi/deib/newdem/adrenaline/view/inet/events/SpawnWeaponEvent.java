package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

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
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.weaponDidSpawn(connection, this);
    }

}
