package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionSender;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

public class PlayerDiscardWeaponEvent implements UserEvent {

    private PlayerColor player;

    private int cardID;

    public PlayerDiscardWeaponEvent(PlayerColor player, int cardID) {
        this.player = player;
        this.cardID = cardID;
    }

    public PlayerColor getPlayer() {
        return player;
    }

    public int getCardID() {
        return cardID;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidRemoveWeapon(connection, this);
    }

    @Override
    public void sendEvent(UserConnectionSender sender) throws ConnectionException {
        sender.sendDiscardWeaponEvent(this);
    }
}
