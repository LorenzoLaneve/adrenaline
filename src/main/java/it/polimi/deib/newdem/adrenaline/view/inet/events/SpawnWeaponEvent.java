package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;

public class SpawnWeaponEvent implements UserEvent {

    private int cardID;
    private Tile tile;

    public SpawnWeaponEvent(Tile tile, int cardID) {
        this.cardID = cardID;

        this.tile = tile;
    }

    public int getWeaponCardID() {
        return cardID;
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.weaponDidSpawn(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendSpawnWeaponEvent(this);
    }
}
