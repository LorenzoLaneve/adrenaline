package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionReceiver;

public class PlayerDisconnectEvent implements UserEvent {

    private PlayerColor color;

    public PlayerDisconnectEvent(PlayerColor color) {
        this.color = color;
    }

    public PlayerColor getPlayerColor() {
        return color;
    }

    @Override
    public void notifyEvent(UserConnection connection, UserConnectionReceiver receiver) {
        receiver.playerDidDisconnect(connection, this);
    }

}
